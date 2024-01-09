package io.innodev.blogapp.service;

import io.innodev.blogapp.config.ModelMapperUtil;
import io.innodev.blogapp.entity.Category;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import io.innodev.blogapp.payloads.CategoryDTO;
import io.innodev.blogapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private static final String RESOURCE_1 = "Category";
    private static final String RESOURCE_2 = "categoryId";
    private final ModelMapperUtil modelMapperUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ModelMapperUtil modelMapperUtil, CategoryRepository categoryRepository) {
        this.modelMapperUtil = modelMapperUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapperUtil.modelMapper().map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapperUtil.modelMapper().map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(RESOURCE_1, RESOURCE_2, categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapperUtil.modelMapper().map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(RESOURCE_1, RESOURCE_2, categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_1, RESOURCE_2, categoryId));
        return modelMapperUtil.modelMapper().map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCatrgoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map((category -> modelMapperUtil.modelMapper().map(category, CategoryDTO.class))).toList();
    }
}
