package com.multitab.category.cate.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryResponseVo {

    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String middleCategoryCode;

}
