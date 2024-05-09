package com.kosign.onshopapi.service.category;

import com.kosign.onshopapi.payload.category.CreateCategoryRequest;
import com.kosign.onshopapi.payload.category.DeleteCategoryRequest;
import com.kosign.onshopapi.payload.category.UpdateCategoryRequest;

public interface CategoryService {
    void create(CreateCategoryRequest payload) throws Throwable;

    void update(Long categoryId, UpdateCategoryRequest payload) throws Throwable;

    void delete(DeleteCategoryRequest payload) throws Throwable;

    Object getAll() throws Throwable;
}
