package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.CategoryRequestDto;
import com.example.bookmanagement.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto saveCategory(CategoryRequestDto dto);
    CategoryResponseDto findBookById(Long id);
    CategoryResponseDto updateBook(Long id,CategoryRequestDto dto);
    List<CategoryResponseDto> findAllBooks();
    void deleteBooks(Long id);
}
