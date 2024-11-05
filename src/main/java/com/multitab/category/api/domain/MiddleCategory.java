package com.multitab.category.api.domain;


import com.multitab.category.common.entity.BaseEntity;
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
//    @JoinColumn(name = "top_category_id", referencedColumnName = "id", nullable = false)
    private TopCategory topCategory;

}
