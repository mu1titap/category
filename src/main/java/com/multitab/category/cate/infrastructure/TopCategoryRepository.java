package com.multitab.category.cate.infrastructure;


import com.multitab.category.cate.domain.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopCategoryRepository extends JpaRepository<TopCategory, Long> {

    Optional<TopCategory> findByCategoryName(String categoryName);

    Optional<TopCategory> findByCategoryCode(String categoryCode);

    Optional<TopCategory> findFirstByOrderByIdDesc();

    boolean existsByCategoryName(String categoryName);

    boolean existsByCategoryCode(String categoryCode);
}
