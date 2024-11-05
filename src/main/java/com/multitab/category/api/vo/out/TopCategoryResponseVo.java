package com.multitab.category.api.vo.out;

import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TopCategoryResponseVo {

    private Long id;
    private String topCategoryCode;
    private String topCategoryName;


}
