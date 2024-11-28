package com.multitab.category.api.application;

import com.multitab.category.api.domain.MiddleCategory;
import com.multitab.category.api.domain.TopCategory;
import com.multitab.category.api.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.api.dto.in.TopCategoryRequestDto;
import com.multitab.category.api.dto.in.UpdateCategoryRequeestDto;
import com.multitab.category.api.dto.out.ChildCategoryResponseDto;
import com.multitab.category.api.dto.out.MiddleCategoryResponseDto;
import com.multitab.category.api.dto.out.TopCategoryResponseDto;
import com.multitab.category.api.infrastructure.MiddleCategoryRepository;
import com.multitab.category.api.infrastructure.TopCategoryRepository;
import com.multitab.category.api.infrastructure.search.CategorySearch;
import com.multitab.category.api.kafka.KafkaProducer;
import com.multitab.category.common.Exception.BaseException;
import com.multitab.category.common.entity.BaseResponseStatus;
import com.multitab.category.common.utils.CategoryCodeGenerator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final TopCategoryRepository topCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final CategorySearch categorySearch;
    private final KafkaProducer kafkaProducer;


    private static final int MAX_CODE_TRIES = 5;  // 최대 재시도 횟수

    @Transactional
    @Override
    public TopCategoryResponseDto createTopCategory(TopCategoryRequestDto topCategoryRequestDto) {

        if (topCategoryRepository.existsByCategoryName(
            topCategoryRequestDto.getTopCategoryName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY_NAME);
        }
        // categoryCode 생성
        String categoryCode = generateUniqueCategoryCode("TC-");

        try {
            TopCategory topCategory = topCategoryRequestDto.toEntity(categoryCode);
            topCategoryRepository.save(topCategory);
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw new BaseException(
                BaseResponseStatus.INTERNAL_SERVER_ERROR);  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return TopCategoryResponseDto.builder()
            .topCategoryName(topCategoryRequestDto.getTopCategoryName())
            .topCategoryCode(categoryCode)
            .categoryType(topCategoryRequestDto.getCategoryType())
            .build();

    }

    @Transactional
    @Override
    public MiddleCategoryResponseDto createMiddleCategory(
        MiddleCategoryRequestDto middleCategoryRequestDto) {
        // unique 제약 조건 확인
        List<MiddleCategoryResponseDto> middleCategoryResponseDtos =
            getMiddleCategories(middleCategoryRequestDto.getTopCategoryCode());

        for (MiddleCategoryResponseDto middleCategoryResponseDto : middleCategoryResponseDtos) {
            if (middleCategoryResponseDto.getMiddleCategoryName()
                .equals(middleCategoryRequestDto.getMiddleCategoryName())) {
                throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY_NAME);
            }
        }
        // create
        String categoryCode = generateUniqueCategoryCode("MC-");
        try {
            TopCategory topCategory = topCategoryRepository.findByCategoryCode(
                middleCategoryRequestDto.getTopCategoryCode()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
            );

            middleCategoryRepository.save(
                middleCategoryRequestDto.toEntity(topCategory, categoryCode));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

        return MiddleCategoryResponseDto.builder()
            .middleCategoryName(middleCategoryRequestDto.getMiddleCategoryName())
            .middleCategoryOrder(middleCategoryRequestDto.getCategoryOrder())
            .topCategoryCode(middleCategoryRequestDto.getTopCategoryCode())
            .middleCategoryCode(categoryCode)
            .build();

    }


    @Transactional
    @Override
    public void updateTopCategory(UpdateCategoryRequeestDto updateCategoryRequeestDto) {
        TopCategory topCategory = topCategoryRepository
            .findByCategoryCode(updateCategoryRequeestDto.getCategoryCode())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        topCategoryRepository.save(updateCategoryRequeestDto.toTopCategory(
            topCategory.getId()));
        log.info("here");
        // kafka publish
        kafkaProducer.sendUpdateMentoring("update-category", updateCategoryRequeestDto);
    }

    @Transactional
    @Override
    public void updateMiddleCategory(UpdateCategoryRequeestDto updateCategoryRequeestDto) {
        MiddleCategory middleCategory = middleCategoryRepository
            .findByCategoryCode(updateCategoryRequeestDto.getCategoryCode())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        middleCategoryRepository.save(updateCategoryRequeestDto.toMiddleCategory(
            middleCategory.getId()));
    }


    @Override
    public void deleteTopCategory(Long topCategoryId) {
        TopCategory topCategory = topCategoryRepository
            .findById(topCategoryId)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        List<ChildCategoryResponseDto> childCategories = categorySearch
            .findChildCategoriesByTopCategory(topCategory.getCategoryCode());

        for (ChildCategoryResponseDto childCategory : childCategories) {
            Long childCategoryId = middleCategoryRepository.findByCategoryCode(
                    childCategory.getCategoryCode()).orElseThrow()
                .getId();
            deleteMiddleCategory(childCategoryId);
        }

        topCategoryRepository.deleteById(topCategoryId);
    }

    @Override
    public void deleteMiddleCategory(Long middleCategoryId) {
        MiddleCategory middleCategory = middleCategoryRepository
            .findById(middleCategoryId)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        middleCategoryRepository.deleteById(middleCategoryId);

    }


    @Transactional(readOnly = true)
    @Override
    public TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode) {

        try {
            TopCategory topCategory = topCategoryRepository
                .findByCategoryCode(topCategoryCode).orElseThrow(
                    () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
                );
            log.info("topCategory : {}", topCategory);
            return TopCategoryResponseDto.builder()
                .id(topCategory.getId())
                .topCategoryName(topCategory.getCategoryName())
                .topCategoryCode(topCategory.getCategoryCode())
                .categoryType(topCategory.getCategoryType())
                .build();
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }


    @Transactional(readOnly = true)
    @Override
    public MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode) {
        //todo 부모 카테고리 코드 안 주어졌을 시 그대로
        try {
            MiddleCategory middleCategory = middleCategoryRepository
                .findByCategoryCode(middleCategoryCode).orElseThrow(
                    () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
                );
            log.info("middleCategory : {}", middleCategory);
            return MiddleCategoryResponseDto.builder()
                .middleCategoryName(middleCategory.getCategoryName())
                .middleCategoryOrder(middleCategory.getCategoryOrder())
                .middleCategoryCode(middleCategory.getCategoryCode())
                .topCategoryCode(middleCategory.getTopCategory().getCategoryCode())
                .build();
        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_PARENT_CATEGORYCODE);
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }


    @Override
    public List<TopCategoryResponseDto> getTopCategories() {
        return topCategoryRepository.findAll().stream().map(
            topCategory -> TopCategoryResponseDto.builder()
                .id(topCategory.getId())
                .topCategoryName(topCategory.getCategoryName())
                .topCategoryCode(topCategory.getCategoryCode())
                .categoryType(topCategory.getCategoryType())
                .build()
        ).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode) {

        try {
            log.info("topCategoryCode : {}", topCategoryCode);
//            TopCategory topCategory = topCategoryRepository.findByCategoryName(topCategoryName)
//                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
            TopCategory topCategory = topCategoryRepository.findByCategoryCode(topCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
            log.info("topCategory : {}", topCategory);

            List<MiddleCategoryResponseDto> middleCategoryResponseDtos = middleCategoryRepository
                .findByTopCategoryCategoryCode(topCategoryCode)
                .stream()
                .map(middleCategory -> MiddleCategoryResponseDto.builder()
                    .middleCategoryName(middleCategory.getCategoryName())
                    .middleCategoryOrder(middleCategory.getCategoryOrder())
                    .middleCategoryCode(middleCategory.getCategoryCode())
                    .topCategoryCode(middleCategory.getTopCategory().getCategoryCode())
                    .build())
                .collect(Collectors.toList());

            log.info("middleCategories : {}", middleCategoryResponseDtos);
            return middleCategoryResponseDtos;

        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode) {
        return categorySearch.findChildCategoriesByTopCategory(categoryCode);
    }

    private String generateUniqueCategoryCode(String prefix) {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String categoryCode = CategoryCodeGenerator.generateCategoryCode(prefix);
            switch (prefix) {
                case "TC-":
                    if (!topCategoryRepository.existsByCategoryCode(categoryCode)) {
                        return categoryCode;  // 중복이 없으면 코드 반환
                    }
                    break;
                case "MC-":
                    if (!middleCategoryRepository.existsByCategoryCode(categoryCode)) {
                        return categoryCode;  // 중복이 없으면 코드 반환
                    }
                    break;
                default:
                    throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
            }

        }
        throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
    }

}
