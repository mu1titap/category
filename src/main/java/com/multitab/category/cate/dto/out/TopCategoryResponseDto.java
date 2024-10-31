package com.multitab.category.cate.dto.out;


import com.multitab.category.cate.vo.out.TopCategoryResponseVo;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopCategoryResponseDto {

    private String topCategoryName;
    private String topCategoryDescription;
    private String topCategoryCode;

    public TopCategoryResponseVo toVo() {
        return TopCategoryResponseVo.builder()
                .topCategoryName(topCategoryName)
                .topCategoryDescription(topCategoryDescription)
                .topCategoryCode(topCategoryCode)
                .build();
    }

}
