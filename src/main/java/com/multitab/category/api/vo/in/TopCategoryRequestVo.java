package com.multitab.category.api.vo.in;

import com.multitab.category.api.domain.CategoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TopCategoryRequestVo {

    private String topCategoryName;
    private CategoryType categoryType;
    @Schema(description = "아이콘 URL (필수 아님)", nullable = true, defaultValue = "")
    private String imageUrl;

}
