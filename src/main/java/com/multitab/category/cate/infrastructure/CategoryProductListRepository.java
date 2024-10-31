package com.multitab.category.cate.infrastructure;


import com.multitab.category.cate.domain.CategoryProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
//    List<ProductCategoryList> findByTopCategoryId(Integer topCategoryId);
//    List<ProductCategoryList> findByMiddleCategoryId(Integer middleCategoryId);
//    List<ProductCategoryList> findByBottomCategoryId(Integer bottomCategoryId);
//    List<ProductCategoryList> findByProductId(Long productId);
}
