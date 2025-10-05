package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
   private CategoryService categoryService ;

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name="pageNumber") Integer pageNumber,@RequestParam(name="pageSize") Integer pageSize) {
      CategoryResponse categoryResponse =  categoryService.getAllCategories(pageNumber,pageSize) ;
        return new ResponseEntity<>(categoryResponse , HttpStatus.FOUND);
    }

 @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
       CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
       return new ResponseEntity<>(savedCategoryDTO , HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){

        CategoryDTO status = categoryService.deleteCategory(categoryId);
return new ResponseEntity<>(status , HttpStatus.OK) ;
    }
    @PutMapping("/api/public/categories/{categoryID}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Long categoryID){
            CategoryDTO savedcategory = categoryService.updateCategory(categoryDTO, categoryID);
            return new ResponseEntity<>(savedcategory,HttpStatus.OK);

    }
}
