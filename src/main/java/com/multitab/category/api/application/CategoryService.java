package com.multitab.category.api.application;


import com.multitab.category.api.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.api.dto.in.TopCategoryRequestDto;
import com.multitab.category.api.dto.in.UpdateCategoryRequeestDto;
import com.multitab.category.api.dto.out.*;

import java.util.List;

public interface CategoryService {


    // 상 카테고리 생성
    TopCategoryResponseDto createTopCategory(TopCategoryRequestDto topCategoryRequestDto);

    // 중 카테고리 생성
    MiddleCategoryResponseDto createMiddleCategory(
        MiddleCategoryRequestDto middleCategoryRequestDto);

    void updateTopCategory(UpdateCategoryRequeestDto updateCategoryRequeestDto);

    void updateMiddleCategory(UpdateCategoryRequeestDto updateCategoryRequeestDto);

    void deleteTopCategory(Long topCategoryId);

    void deleteMiddleCategory(Long middleCategoryId);


    TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode);

    MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode);

    List<TopCategoryResponseDto> getTopCategories();

    List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode); // name -> code

    // 대 카테고리의 자식 카테고리(중) 조회
    List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode);
}
