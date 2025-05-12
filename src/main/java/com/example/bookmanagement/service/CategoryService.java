package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.CategoryRequestDto;
import com.example.bookmanagement.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto saveCategory(CategoryRequestDto dto);
    CategoryResponseDto findCategoryById(Long id);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);
    List<CategoryResponseDto> findAllCategories();
    void deleteCategory(Long id);
}