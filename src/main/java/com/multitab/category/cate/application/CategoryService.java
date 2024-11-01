package com.multitab.category.cate.application;




import com.multitab.category.cate.dto.in.MiddleCategoryRequestDto;
import com.multitab.category.cate.dto.in.TopCategoryRequestDto;
import com.multitab.category.cate.dto.out.*;

import java.util.List;

public interface CategoryService {

    // 메인 카테고리 조회
    List<MainCategoryResponseDto> findMainCategoryResponseDto();

    // 상 카테고리 생성
    TopCategoryResponseDto createTopCategory(TopCategoryRequestDto topCategoryRequestDto);
    // 중 카테고리 생성
    void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto);
    // 하 카테고리 생성

    void updateTopCategory(TopCategoryRequestDto topCategoryRequestDto);
    void updateMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto);

    void deleteTopCategory(Long topCategoryId);
    void deleteMiddleCategory(Long middleCategoryId);
    void deleteBottomCategory(Long bottomCategoryId);

    TopCategoryResponseDto getTopCategory(Long topCategoryId);
    TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode);
    MiddleCategoryResponseDto getMiddleCategory(Long middleCategoryId);
    MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode);


    List<TopCategoryResponseDto> getTopCategories();
    List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode); // name -> code

    // 대 카테고리의 자식 카테고리(중) 조회
    List<ChildCategoryResponseDto> findChildCategoriesByTopCategory (String categoryCode);
}
