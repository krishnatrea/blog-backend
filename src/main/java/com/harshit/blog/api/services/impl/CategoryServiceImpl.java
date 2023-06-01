package com.harshit.blog.api.services.impl;

import com.harshit.blog.api.entities.Category;
import com.harshit.blog.api.exceptions.ResourceNotFoundException;
import com.harshit.blog.api.payloads.CategoryDto;
import com.harshit.blog.api.repositories.CategoryRepository;
import com.harshit.blog.api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "id", categoryId.toString()));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return categoryToDto(updatedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "id", categoryId.toString()));
        return categoryToDto(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "id", categoryId.toString()));
        categoryRepository.delete(category);
    }

    private Category dtoToCategory(CategoryDto categoryDto)  {
        return modelMapper.map(categoryDto,Category.class);
    }

    private CategoryDto categoryToDto(Category category) {
        return modelMapper.map(category,CategoryDto.class);
    }
}
