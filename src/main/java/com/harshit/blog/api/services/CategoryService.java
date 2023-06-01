package com.harshit.blog.api.services;

import com.harshit.blog.api.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategory(Integer categoryId);

    void deleteCategory(Integer categoryId);
}
