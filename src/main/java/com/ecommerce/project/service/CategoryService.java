package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

public interface CategoryService {
CategoryResponse getAllCategories(Integer pageNumber , Integer pagesize);
CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryID);
    CategoryDTO updateCategory(CategoryDTO categoryDTO ,Long categoryID);

}
