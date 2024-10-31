package com.multitab.category.cate.dto.in;


import com.multitab.category.cate.domain.BottomCategory;
import com.multitab.category.cate.domain.MiddleCategory;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryRequestDto {

    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String middleCategoryCode;

    public BottomCategory toEntity(MiddleCategory middleCategory, String bottomCategoryCode) {
        return BottomCategory.builder()
                .categoryName(bottomCategoryName)
                .categoryDescription(bottomCategoryDescription)
                .categoryCode(bottomCategoryCode)
                .middleCategory(middleCategory)
                .build();
    }

}
