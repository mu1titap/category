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
@Table(name = "middle_category", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"category_code", "category_name"})})
@Entity
public class MiddleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name", nullable = false, length = 30)
    private String categoryName;
    @Column(name = "category_order", nullable = false)
    private Integer categoryOrder;
    @Column(name = "category_code", nullable = false, length = 20)
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private TopCategory topCategory;

}
