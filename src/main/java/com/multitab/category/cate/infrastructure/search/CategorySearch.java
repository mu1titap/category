package com.multitab.category.cate.infrastructure.search;

import com.multitab.category.cate.dto.out.ChildCategoryResponseDto;
import java.util.List;

public interface CategorySearch {

    // 대 카테고리의 하위 카테고리(중) 조회
    List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode);

}
