package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.CategoryRequestDto;
import com.example.bookmanagement.dto.response.CategoryResponseDto;
import com.example.bookmanagement.entity.Category;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.CategoryRepository;
import com.example.bookmanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto saveCategory(CategoryRequestDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category saved = categoryRepository.save(category);
        return convertToDto(saved);
    }

    @Override
    public CategoryResponseDto findBookById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return convertToDto(category);
    }

    @Override
    public CategoryResponseDto updateBook(Long id, CategoryRequestDto dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " +id));

        category.setName(dto.getName());

        Category updated = categoryRepository.save(category);
        return convertToDto(updated);
    }

    @Override
    public List<CategoryResponseDto> findAllBooks() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> convertToDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooks(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDto convertToDto(Category category){
        return modelMapper.map(category,CategoryResponseDto.class);
    }

}
