package com.multitab.category.cate.dto.out;

import com.multitab.category.cate.vo.out.MiddleCategoryResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
public class MiddleCategoryResponseDto {

    private String middleCategoryName;
    private Integer middleCategoryOrder;
    private String middleCategoryCode;
    private String topCategoryCode;

    public MiddleCategoryResponseVo toVo() {
        return MiddleCategoryResponseVo.builder()
                .middleCategoryName(middleCategoryName)
                .middleCategoryOrder(middleCategoryOrder)
                .middleCategoryCode(middleCategoryCode)
                .topCategoryCode(topCategoryCode)
                .build();
    }
    @QueryProjection
    public MiddleCategoryResponseDto(String middleCategoryName, Integer middleCategoryOrder,
                                     String middleCategoryCode, String topCategoryCode) {
        this.middleCategoryName = middleCategoryName;
        this.middleCategoryOrder = middleCategoryOrder;
        this.middleCategoryCode = middleCategoryCode;
        this.topCategoryCode = topCategoryCode;
    }
}
