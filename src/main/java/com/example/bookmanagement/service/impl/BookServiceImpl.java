package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    // BookRequestDto → Book → save() → BookResponseDto
    @Override
    public BookResponseDto createBook(BookRequestDto dto) {
        Book book = modelMapper.map(dto, Book.class); // BookRequestDto → Entity(Book)
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponseDto.class); // Entity(Book) → BookResponseDto
    }

    // Tüm kitaplar (List<Book>) çekilir → stream ile DTO’ya çevrilir → liste olarak döner
    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    // id ile kitap bulunur → Book → BookResponseDto’ya çevrilir → JSON olarak döner
    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return modelMapper.map(book, BookResponseDto.class);
    }

    // ID ile kitap bulunur → gelen DTO’daki alanlarla güncellenir → save() edilir → DTO’ya çevrilip döner
    @Override
    public BookResponseDto updateBookById(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());

        Book updateBook = bookRepository.save(book);
        return modelMapper.map(updateBook, BookResponseDto.class);
    }

    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.delete(book);

    }

    @Override
    public List<BookResponseDto> getBooksByAuthor(String author) {

        List<Book> books = bookRepository.findByAuthor(author);

        return books.stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

}
