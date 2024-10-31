package com.multitab.category.cate.dto.in;


import com.multitab.category.cate.domain.MiddleCategory;
import com.multitab.category.cate.domain.TopCategory;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleCategoryRequestDto {

    private String middleCategoryName;
    private Integer categoryOrder;
    private String topCategoryCode;

    public MiddleCategory toEntity(TopCategory topCategory, String middleCategoryCode) {
        return MiddleCategory.builder()
                .categoryName(middleCategoryName)
                .categoryOrder(categoryOrder)
                .categoryCode(middleCategoryCode)
                .topCategory(topCategory)
                .build();
    }

}
