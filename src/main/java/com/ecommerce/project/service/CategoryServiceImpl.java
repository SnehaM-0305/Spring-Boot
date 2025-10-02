package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService      {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll() ;
        if(categories.isEmpty())
        {
            throw new APIException("No category created till now");}

            List<CategoryDTO> categoryDTOS =categories.stream().map(category -> modelMapper
                    .map(category,CategoryDTO.class))
                    .toList();

            CategoryResponse categoryResponse =new CategoryResponse();
            categoryResponse.setContent(categoryDTOS);

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        //how you will handle the scenarios in which u need a method from repository but it does not exists?
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB!=null)
        {
            throw new APIException("Category with the name " +category.getCategoryName() +" already exists");
        }
       Category savedCategory =  categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        Category existingCategory = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , categoryID));
        categoryRepository.delete(existingCategory);
        return "Category with category Id " + categoryID + " deleted successfully";

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO ,Long categoryID) {
        Category savedCategory = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "categoryId" , categoryID));
        Category category = modelMapper.map(categoryDTO ,Category.class);

        savedCategory.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryId(categoryID);
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

}
