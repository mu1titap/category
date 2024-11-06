package com.multitab.category.api.presentation;

import com.multitab.category.api.application.CategoryService;
import com.multitab.category.common.entity.BaseResponse;
import com.multitab.category.api.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.api.dto.in.TopCategoryRequestDto;
import com.multitab.category.api.dto.in.UpdateCategoryRequeestDto;
import com.multitab.category.api.dto.out.ChildCategoryResponseDto;
import com.multitab.category.api.dto.out.MiddleCategoryResponseDto;
import com.multitab.category.api.dto.out.TopCategoryResponseDto;
import com.multitab.category.api.vo.in.MiddleCategoryRequestVo;
import com.multitab.category.api.vo.in.TopCategoryRequestVo;
import com.multitab.category.api.vo.in.UpdateCategoryRequestVo;
import com.multitab.category.api.vo.out.ChildCategoryResponseVo;
import com.multitab.category.api.vo.out.MiddleCategoryResponseVo;
import com.multitab.category.api.vo.out.TopCategoryResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;


    // 대 카테고리 생성
    @PostMapping("/top-category")
    @Operation(summary = "대 카테고리 생성", description = "대 카테고리 생성 카테고리명, 소개 입력")
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

    @Operation(summary = "대 카테고리의 자식 카테고리 조회", description = "중 카테고리의 자식까지만 조회됨. 하 카테고리는 조회안됨")
    @GetMapping("/top-category/child/{categoryCode}")
    public BaseResponse<List<ChildCategoryResponseVo>> findChildCategoriesByTopCategoryV1(
        @PathVariable("categoryCode") String categoryCode) {

        return new BaseResponse<>(
            categoryService.findChildCategoriesByTopCategory(categoryCode)
                .stream()
                .map(ChildCategoryResponseDto::toVo)
                .collect(Collectors.toList()));
    }


    @Operation(summary = "대 카테고리의 수정", description = "parentCode 아무 값이나 상관 없음")
    @PutMapping("/top-category")
    public BaseResponse<Void> updateTopCategory(
        @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {

        categoryService.updateTopCategory(UpdateCategoryRequeestDto.of(updateCategoryRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "중 카테고리의 수정")
    @PutMapping("/middle-category")
    public BaseResponse<Void> updateMiddleCategory(
        @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {

        categoryService.updateMiddleCategory(UpdateCategoryRequeestDto.of(updateCategoryRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "대 카테고리의 삭제")
    @DeleteMapping("/top-category")
    public BaseResponse<Void> deleteTopCategory(
        @RequestParam Long topCategoryId) {

        categoryService.deleteTopCategory(topCategoryId);
        return new BaseResponse<>();
    }

    @Operation(summary = "중 카테고리의 삭제")
    @DeleteMapping("/middle-category")
    public BaseResponse<Void> deleteMiddleCategory(
        @RequestParam Long middleCategoryId) {

        categoryService.deleteMiddleCategory(middleCategoryId);
        return new BaseResponse<>();
    }


}