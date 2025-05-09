package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.entity.Book;
import java.util.List;


public interface BookService {
    BookResponseDto createBook(BookRequestDto dto);
    BookResponseDto getBookById(Long id);
    BookResponseDto updateBookById(Long id, BookRequestDto dto);
    List<BookResponseDto> getAllBooks();
    void deleteBook(Long id);
    List<BookResponseDto> getBooksByAuthor(String author);
}
