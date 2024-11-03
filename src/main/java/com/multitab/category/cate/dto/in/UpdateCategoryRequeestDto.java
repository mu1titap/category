package com.multitab.category.cate.dto.in;

import com.multitab.category.cate.domain.MiddleCategory;
import com.multitab.category.cate.domain.TopCategory;
import com.multitab.category.cate.vo.in.UpdateCategoryRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateCategoryRequeestDto {

    private String parentCategoryCode;
    private String categoryCode;
    private String categoryName;
    private Integer categoryOrder;

    public static UpdateCategoryRequeestDto of(UpdateCategoryRequestVo updateCategoryRequestVo) {
        return UpdateCategoryRequeestDto.builder()
            .parentCategoryCode(updateCategoryRequestVo.getParentCategoryCode())
            .categoryCode(updateCategoryRequestVo.getCategoryCode())
            .categoryName(updateCategoryRequestVo.getCategoryName())
            .categoryOrder(updateCategoryRequestVo.getCategoryOrder())
            .build();
    }

    public TopCategory toTopCategory(Long id) {
        return TopCategory.builder()
            .id(id)
            .categoryCode(categoryCode)
            .categoryName(categoryName)
            .categoryOrder(categoryOrder)
            .build();
    }

    public MiddleCategory toMiddleCategory(Long id) {
        return MiddleCategory.builder()
            .id(id)
            .categoryCode(categoryCode)
            .categoryName(categoryName)
            .categoryOrder(categoryOrder)
            .build();
    }


}
