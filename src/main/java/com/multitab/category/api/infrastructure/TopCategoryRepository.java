package com.multitab.category.api.infrastructure;


import com.multitab.category.api.domain.TopCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopCategoryRepository extends JpaRepository<TopCategory, Long> {

    Optional<TopCategory> findByCategoryName(String categoryName);

    Optional<TopCategory> findByCategoryCode(String categoryCode);

    Optional<TopCategory> findFirstByOrderByIdDesc();

    boolean existsByCategoryName(String categoryName);

    boolean existsByCategoryCode(String categoryCode);

    @Query("SELECT COALESCE(MAX(tc.categoryOrder), 0) FROM TopCategory tc")
    Integer findMaxCategoryOrder();

}
