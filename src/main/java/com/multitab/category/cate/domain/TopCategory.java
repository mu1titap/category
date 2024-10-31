package com.multitab.category.cate.domain;


import com.multitab.category.cate.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "top_category", uniqueConstraints = {@UniqueConstraint(columnNames = {"category_code", "category_name"} )})
public class TopCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name" ,nullable = false, length = 30)
    private String categoryName;
    @Column(name = "category_order" ,nullable = false)
    private Integer categoryOrder;
    @Column(name = "category_code" ,nullable = false, length = 20)
    private String categoryCode;
}