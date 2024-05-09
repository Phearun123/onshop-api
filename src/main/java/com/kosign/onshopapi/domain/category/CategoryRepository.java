package com.kosign.onshopapi.domain.category;

import com.kosign.onshopapi.payload.category.CreateCategoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query("select c from Category c where c.category_name = ?1")
    Optional<Category> findCategoryByName(String category_name);

    @Query("select c from Category c where c.cid = ?1 and c.category_name = ?2")
    Optional<Category> findCategoryByIdAndName(Long cid, String categoryName);
}
