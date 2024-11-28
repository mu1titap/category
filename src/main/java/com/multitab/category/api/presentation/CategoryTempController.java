package com.multitab.category.api.presentation;

import com.multitab.category.api.application.CategoryService;
import com.multitab.category.api.dto.in.UpdateCategoryRequeestDto;
import com.multitab.category.api.dto.out.ChildCategoryResponseDto;
import com.multitab.category.api.vo.in.UpdateCategoryRequestVo;
import com.multitab.category.api.vo.out.ChildCategoryResponseVo;
import com.multitab.category.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "카테고리 관련 보조 API")
public class CategoryTempController {

    private final CategoryService categoryService;

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
