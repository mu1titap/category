package com.multitab.category.cate.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleCategoryResponseVo {

    private String middleCategoryCode;
    private String middleCategoryName;
    private Integer middleCategoryOrder;
    private String topCategoryCode;

}
