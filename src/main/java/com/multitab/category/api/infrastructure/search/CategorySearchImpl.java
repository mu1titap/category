package com.multitab.category.api.infrastructure.search;


import static com.multitab.category.api.domain.QMiddleCategory.middleCategory;

import com.multitab.category.api.dto.out.ChildCategoryResponseDto;
import com.multitab.category.api.dto.out.QChildCategoryResponseDto;
import com.multitab.category.api.infrastructure.TopCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class CategorySearchImpl implements CategorySearch {

    private final JPAQueryFactory queryFactory;
    private final TopCategoryRepository topCategoryRepository;

    @Override
    public List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode) {
        Long parent_id = topCategoryRepository.findByCategoryCode(categoryCode).orElseThrow()
            .getId();
        return queryFactory
            .select(new QChildCategoryResponseDto(middleCategory.categoryCode,
                middleCategory.categoryName))
            .from(middleCategory)
            .where(middleCategory.topCategory.id.eq(parent_id))
            .fetch();
    }


}
