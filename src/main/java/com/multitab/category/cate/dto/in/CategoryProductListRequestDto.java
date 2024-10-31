package com.multitab.category.cate.dto.in;


import com.multitab.category.cate.domain.CategoryProductList;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductListRequestDto {
    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private Long productId;

//    public CategoryProductList toEntity(Product product) {
//        return CategoryProductList.builder()
//                .topCategoryCode(topCategoryCode)
//                .middleCategoryCode(middleCategoryCode)
//                .bottomCategoryCode(bottomCategoryCode)
//                .product(product)
//                .build();
//    }
}
