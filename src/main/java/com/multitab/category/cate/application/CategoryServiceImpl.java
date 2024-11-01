package com.multitab.category.cate.application;
import com.multitab.category.cate.common.Exception.BaseException;
import com.multitab.category.cate.common.entity.BaseResponseStatus;
import com.multitab.category.cate.common.utils.CategoryCodeGenerator;
import com.multitab.category.cate.domain.MiddleCategory;
import com.multitab.category.cate.domain.TopCategory;
import com.multitab.category.cate.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.cate.dto.in.TopCategoryRequestDto;
import com.multitab.category.cate.dto.out.ChildCategoryResponseDto;
import com.multitab.category.cate.dto.out.MainCategoryResponseDto;
import com.multitab.category.cate.dto.out.MiddleCategoryResponseDto;
import com.multitab.category.cate.dto.out.TopCategoryResponseDto;
import com.multitab.category.cate.infrastructure.MiddleCategoryRepository;
import com.multitab.category.cate.infrastructure.TopCategoryRepository;
import com.multitab.category.cate.infrastructure.search.CategorySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final TopCategoryRepository topCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final CategorySearch categorySearch;


    private static final int MAX_CODE_TRIES = 5;  // 최대 재시도 횟수

    @Transactional(readOnly = true)
    @Override
    public List<MainCategoryResponseDto> findMainCategoryResponseDto() {
        return categorySearch.findMainCategoryResponseDto();
    }

    @Transactional
    @Override
    public TopCategoryResponseDto createTopCategory(TopCategoryRequestDto topCategoryRequestDto) {

        if (topCategoryRepository.existsByCategoryName(topCategoryRequestDto.getTopCategoryName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY_NAME);
        }

        String categoryCode = generateUniqueCategoryCode("TC-");
        try {
            topCategoryRepository.save(topCategoryRequestDto.toEntity(categoryCode));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return TopCategoryResponseDto.builder()
            .topCategoryName(topCategoryRequestDto.getTopCategoryName())
               .topCategoryOrder(topCategoryRequestDto.getCategoryOrder())
               .topCategoryCode(categoryCode)
               .build();

    }

    @Transactional
    @Override
    public MiddleCategoryResponseDto createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {

        if( middleCategoryRepository.existsByCategoryName(middleCategoryRequestDto.getMiddleCategoryName()) ){
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY_NAME);
        }

        String categoryCode = generateUniqueCategoryCode("MC-");
        try {
            TopCategory topCategory = topCategoryRepository.findByCategoryCode(
                    middleCategoryRequestDto.getTopCategoryCode()).orElseThrow(
                    () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)
            );


            middleCategoryRepository.save(middleCategoryRequestDto.toEntity(topCategory, categoryCode));
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


    @Override
    public void updateTopCategory(TopCategoryRequestDto topCategoryRequestDto) {
    }

    @Override
    public void updateMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {

    }


    @Override
    public void deleteTopCategory(Long topCategoryId) {

    }

    @Override
    public void deleteMiddleCategory(Long middleCategoryId) {

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
                    .topCategoryName(topCategory.getCategoryName())
                    .topCategoryOrder(topCategory.getCategoryOrder())
                    .topCategoryCode(topCategory.getCategoryCode())
                    .build();
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }


    @Transactional(readOnly = true)
    @Override
    public MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode) {

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
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }


    @Override
    public List<TopCategoryResponseDto> getTopCategories() {
        return topCategoryRepository.findAll().stream().map(
                topCategory -> TopCategoryResponseDto.builder()
                        .topCategoryName(topCategory.getCategoryName())
                        .topCategoryOrder(topCategory.getCategoryOrder())
                        .topCategoryCode(topCategory.getCategoryCode())
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
