package com.multitab.category.cate.dto.out;


import com.multitab.category.cate.vo.out.BottomCategoryResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryResponseDto {

    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String middleCategoryCode;

    public BottomCategoryResponseVo toVo() {
        return BottomCategoryResponseVo.builder()
                .bottomCategoryCode(bottomCategoryCode)
                .bottomCategoryName(bottomCategoryName)
                .bottomCategoryDescription(bottomCategoryDescription)
                .middleCategoryCode(middleCategoryCode)
                .build();
    }

}