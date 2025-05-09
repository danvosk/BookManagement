package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto saveBook(@RequestBody BookRequestDto dto) {
        return bookService.saveBook(dto);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        return bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/all")
    public List<BookResponseDto> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/author")
    public List<BookResponseDto> getBooksByAuthor(@RequestParam String name) {
        return bookService.getBooksByAuthor(name);
    }

}
