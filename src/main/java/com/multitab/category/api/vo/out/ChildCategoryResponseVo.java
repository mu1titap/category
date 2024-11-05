package com.multitab.category.api.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildCategoryResponseVo {

    private String categoryCode;

    private String categoryName;
}
