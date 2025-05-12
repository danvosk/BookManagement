package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto saveBook(BookRequestDto dto);
    BookResponseDto findBookById(Long id);
    BookResponseDto updateBook(Long id, BookRequestDto dto);
    List<BookResponseDto> findAllBooks();
    void deleteBook(Long id);
    List<BookResponseDto> getBooksByAuthor(String author);
}