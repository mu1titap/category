package com.multitab.category.cate.domain;

import com.multitab.category.cate.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryProductList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;

    // TODO 삭제?
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Product product;

}