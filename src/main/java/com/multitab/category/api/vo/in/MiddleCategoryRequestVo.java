package com.multitab.category.api.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MiddleCategoryRequestVo {

    private String middleCategoryName;
    private Integer categoryOrder;
    private String topCategoryCode;

}
