package com.schwarz.libraryapp.controller;


import com.schwarz.libraryapp.entity.Category;
import com.schwarz.libraryapp.payload.ApiResponse;
import com.schwarz.libraryapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/add-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category){

        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping(path = "/all-categories")
    public List<?> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/get-category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){

        if(!categoryService.existById(id)){
            return new ResponseEntity(new ApiResponse(false,"this category doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(categoryService.getCategory(id));
    }


    @PutMapping( "/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){

        if(!categoryService.existById(id)){

            return new ResponseEntity(new ApiResponse(false,"this category doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(categoryService.updateCategory(id,category));
    }

    @DeleteMapping(path = "/delete-category/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){

        if(!categoryService.existById(id)) {
            return new ResponseEntity(new ApiResponse(false, "this category doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }


}
