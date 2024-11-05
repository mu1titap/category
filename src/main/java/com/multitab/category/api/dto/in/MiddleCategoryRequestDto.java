package com.multitab.category.api.dto.in;


import com.multitab.category.api.domain.MiddleCategory;
import com.multitab.category.api.domain.TopCategory;
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
