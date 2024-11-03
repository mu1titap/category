package com.multitab.category.cate.vo.in;

import lombok.Getter;

@Getter
public class UpdateCategoryRequestVo {
    // parentCategoryCode가 null일 시 topCategory
    private String parentCategoryCode;
    private String categoryCode;
    private String categoryName;
    private Integer categoryOrder;
}
