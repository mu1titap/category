package com.multitab.category.api.infrastructure;


import com.multitab.category.api.domain.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {

    List<MiddleCategory> findByTopCategoryCategoryCode(String topCategoryCode);

    List<MiddleCategory> findByTopCategoryCategoryName(String topCategoryCode);

    Optional<MiddleCategory> findByCategoryCode(String categoryCode);

    boolean existsByCategoryCode(String categoryCode);

    boolean existsByCategoryName(String categoryName);
}
