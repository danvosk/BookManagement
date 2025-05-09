package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/by-author")
    public List<BookResponseDto> getByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBookById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 - No Content
    }

}
