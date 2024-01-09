package io.innodev.blogapp.service;

import io.innodev.blogapp.payloads.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    //create
    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    //update
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDTO getCategory(Integer categoryId);

    //getAll
    public List<CategoryDTO> getAllCatrgoryList();
}
