package com.kosign.onshopapi.controller.category;

import com.kosign.onshopapi.controller.OnShopRestController;
import com.kosign.onshopapi.domain.category.CategoryRepository;
import com.kosign.onshopapi.payload.category.CreateCategoryRequest;
import com.kosign.onshopapi.payload.category.DeleteCategoryRequest;
import com.kosign.onshopapi.payload.category.UpdateCategoryRequest;
import com.kosign.onshopapi.service.category.CategoryService;
import com.kosign.onshopapi.utils.PasswordUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/os/v1/category")
@RequiredArgsConstructor
public class CategoryController extends OnShopRestController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Object create(@RequestBody @Valid CreateCategoryRequest payload) throws Throwable {
        categoryService.create(payload);
        return ok();
    }

    @PutMapping("/{category_id}")
    public Object update(@PathVariable Long category_id, @RequestBody @Valid UpdateCategoryRequest payload) throws Throwable {
        categoryService.update(category_id, payload);
        return ok();
    }

    @DeleteMapping("")
    public Object delete(@RequestBody @Valid DeleteCategoryRequest payload) throws Throwable {
        categoryService.delete(payload);
        return ok();
    }

    @GetMapping("")
    public Object getAll() throws Throwable {
        return ok(categoryService.getAll());
    }

}
