package com.multitab.category.cate.infrastructure.search;



import static com.multitab.category.cate.domain.QMiddleCategory.middleCategory;
import static com.multitab.category.cate.domain.QTopCategory.topCategory;

import com.multitab.category.cate.dto.out.ChildCategoryResponseDto;
import com.multitab.category.cate.dto.out.MainCategoryResponseDto;
import com.multitab.category.cate.dto.out.QChildCategoryResponseDto;
import com.multitab.category.cate.dto.out.QMainCategoryResponseDto;
import com.multitab.category.cate.infrastructure.TopCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class CategorySearchImpl implements  CategorySearch{

    private final JPAQueryFactory queryFactory;
    private final TopCategoryRepository topCategoryRepository;

    @Override
    public List<MainCategoryResponseDto> findMainCategoryResponseDto() {
        return queryFactory
                .select(new QMainCategoryResponseDto(topCategory.categoryCode,
                                                     topCategory.categoryName,
                                                     topCategory.imageUrl))
                .from(topCategory)
                .orderBy(topCategory.categoryName.asc())
                .fetch();

    }

    @Override
    public List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode) {
        Integer parent_id = topCategoryRepository.findByCategoryCode(categoryCode).orElseThrow().getId();
        return queryFactory
                .select(new QChildCategoryResponseDto(middleCategory.categoryCode,middleCategory.categoryName))
                .from(middleCategory)
                .where(middleCategory.topCategory.id.eq(parent_id))
                .fetch();
    }




}
