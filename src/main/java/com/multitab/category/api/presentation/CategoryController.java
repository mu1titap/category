package com.multitab.category.api.presentation;

import com.multitab.category.api.application.CategoryService;
import com.multitab.category.api.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.api.dto.in.TopCategoryRequestDto;
import com.multitab.category.api.dto.out.MiddleCategoryResponseDto;
import com.multitab.category.api.dto.out.TopCategoryResponseDto;
import com.multitab.category.api.vo.in.MiddleCategoryRequestVo;
import com.multitab.category.api.vo.in.TopCategoryRequestVo;
import com.multitab.category.api.vo.out.MiddleCategoryResponseVo;
import com.multitab.category.api.vo.out.TopCategoryResponseVo;
import com.multitab.category.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "카테고리 관련 주요 API")
public class CategoryController {

    private final CategoryService categoryService;


    // 대 카테고리 생성
    @PostMapping("/top-category")
    @Operation(summary = "대 카테고리 생성", description = "대 카테고리 생성 카테고리명, 소개 입력 <br>"
        + "categoryType 은 INDUSTRY, DOMAIN 으로 구분")
    public BaseResponse<TopCategoryResponseVo> createTopCategory(
        @RequestBody TopCategoryRequestVo topCategoryRequestVo) {

        log.info("topCategoryRequestVo : {}", topCategoryRequestVo);
        TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
            .topCategoryName(topCategoryRequestVo.getTopCategoryName())
            .build();
        ;
        return new BaseResponse<>(categoryService.createTopCategory(topCategoryRequestDto).toVo());
    }

    @Operation(summary = "대 카테고리 단건 조회", description = "카테고리 코드로 대 카테고리 단건 조회")
    @GetMapping("/top-category/{topCategoryCode}")
    public BaseResponse<TopCategoryResponseVo> getTopCategory(
        @PathVariable("topCategoryCode") String topCategoryCode) {
        log.info("topCategoryCode : {}", topCategoryCode);
        return new BaseResponse<>(
            categoryService.getTopCategoryByCategoryCode(topCategoryCode).toVo());
    }

    @Operation(summary = "중 카테고리 생성", description = "중 카테고리 생성 카테고리명, 소개 , 대 카테고리 코드 입력")
    @PostMapping("/middle-category")
    public BaseResponse<MiddleCategoryResponseVo> createMiddleCategory(
        @RequestBody MiddleCategoryRequestVo middleCategoryRequestVo) {

        MiddleCategoryRequestDto middleCategoryRequestDto = MiddleCategoryRequestDto.builder()
            .middleCategoryName(middleCategoryRequestVo.getMiddleCategoryName())
            .categoryOrder(middleCategoryRequestVo.getCategoryOrder())
            .topCategoryCode(middleCategoryRequestVo.getTopCategoryCode())
            .build();
        log.info("middleCategoryRequestDto : {}", middleCategoryRequestDto);

        return new BaseResponse<>(
            categoryService.createMiddleCategory(middleCategoryRequestDto).toVo());
    }

    @Operation(summary = "중 카테고리의 단건 조회")
    @GetMapping("/middle-category/{middleCategoryCode}")
    public BaseResponse<MiddleCategoryResponseVo> getMiddleCategory(
        @PathVariable("middleCategoryCode") String middleCategoryCode) {
        log.info("middleCategoryCode : {}", middleCategoryCode);
        return new BaseResponse<>(
            categoryService.getMiddleCategoryByCategoryCode(middleCategoryCode).toVo());
    }

    @Operation(summary = "대 카테고리 목록 조회")
    @GetMapping("/top-categories")
    public BaseResponse<List<TopCategoryResponseVo>> getTopCategories() {

        return new BaseResponse<>(
            categoryService.getTopCategories()
                .stream()
                .map(TopCategoryResponseDto::toVo)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "대 카테고리의 자식 카테고리 목록 조회")
    @GetMapping("/middle-categories/{topCategoryCode}")
    public BaseResponse<List<MiddleCategoryResponseVo>> getMiddleCategories(
        @PathVariable("topCategoryCode") String topCategoryCode) {

        return new BaseResponse<>(
            categoryService.getMiddleCategories(topCategoryCode)
                .stream()
                .map(MiddleCategoryResponseDto::toVo)
                .collect(Collectors.toList()));
    }


}