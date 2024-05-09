package com.kosign.onshopapi.service.category;

import com.kosign.onshopapi.common.api.StatusCode;
import com.kosign.onshopapi.domain.category.Category;
import com.kosign.onshopapi.domain.category.CategoryRepository;
import com.kosign.onshopapi.domain.user.User;
import com.kosign.onshopapi.exception.BusinessException;
import com.kosign.onshopapi.payload.category.CreateCategoryRequest;
import com.kosign.onshopapi.payload.category.DeleteCategoryRequest;
import com.kosign.onshopapi.payload.category.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public void create(CreateCategoryRequest payload) throws Throwable {

        var categoryExist = categoryRepository.findCategoryByName(payload.categoryName()).orElse(new Category());
        if (StringUtils.isNotEmpty(categoryExist.getCategory_name())) {
            throw new BusinessException(StatusCode.CATEGORY_EXISTED);
        }

        Category category = Category.builder()
                .category_name(payload.categoryName())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void update(Long categoryId, UpdateCategoryRequest payload) throws Throwable {

        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(StatusCode.CATEGORY_NOT_FOUND));

        categoryRepository.findCategoryByName(payload.categoryName())
                        .ifPresent(categoryName -> {
                            throw new BusinessException(StatusCode.CATEGORY_EXISTED);
                        });

        category.setCategory_name(payload.categoryName());

        categoryRepository.save(category);

    }

    @Override
    public void delete(DeleteCategoryRequest payload) throws Throwable {

        payload.categoryId().forEach(categoryId -> {
            var category = categoryRepository.findById(categoryId).orElseThrow(() -> new BusinessException(StatusCode.CATEGORY_NOT_FOUND));

            categoryRepository.delete(category);
        });
    }

    @Override
    public Object getAll() throws Throwable {
        return categoryRepository.findAll();
    }
}
