package io.innodev.blogapp.controller;

import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.payloads.CategoryDTO;
import io.innodev.blogapp.service.CategoryService;
import io.innodev.blogapp.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {


    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //create
    @PostMapping(value = "/createCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryCreated = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    //update
    @PutMapping(value = "/updateCategory/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
        CategoryDTO updateCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    //get
    @GetMapping("/getCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) {
        CategoryDTO category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //getAll
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDTO>> getCategoryList() {
        List<CategoryDTO> allCatrgoryList = categoryService.getAllCatrgoryList();
        return new ResponseEntity<>(allCatrgoryList, HttpStatus.OK);
    }

    //delete
    @DeleteMapping(value = "/deleteCategory/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new Message("Category deleted successfully : " + categoryId, true, new Date().toString()), HttpStatus.OK);
    }

}
