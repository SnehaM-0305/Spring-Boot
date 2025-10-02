package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
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
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories =  categoryService.getAllCategories() ;
        return new ResponseEntity<>(categories , HttpStatus.FOUND);
    }

 @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
       categoryService.createCategory(category);
       return new ResponseEntity<>("Created" , HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){

String status = categoryService.deleteCategory(categoryId);
return new ResponseEntity<>(status , HttpStatus.OK) ;
    }
    @PutMapping("/api/public/categories/{categoryID}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category , @PathVariable Long categoryID){
            categoryService.updateCategory(category, categoryID);
            return new ResponseEntity<>("Category with category id "+ categoryID +" updated" ,HttpStatus.OK);

    }
}
