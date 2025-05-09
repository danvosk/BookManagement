package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.CategoryRepository;
import com.example.bookmanagement.repository.PublisherRepository;
import com.example.bookmanagement.repository.ShopRepository;
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
    private final ShopRepository shopRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto saveBook(BookRequestDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        book.setShop(shopRepository.findById(dto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found")));
        book.setPublisher(publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found")));
        book.setCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPage(dto.getPage());
        book.setShop(shopRepository.findById(dto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found")));
        book.setPublisher(publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found")));
        book.setCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        return convertToDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDto findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return convertToDto(book);
    }

    @Override
    public List<BookResponseDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> convertToDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDto> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author)
                .stream()
                .map(book -> convertToDto(book))
                .collect(Collectors.toList());
    }

    private BookResponseDto convertToDto(Book book) {
        BookResponseDto dto = modelMapper.map(book, BookResponseDto.class);
        dto.setShopName(book.getShop().getName());
        dto.setPublisherName(book.getPublisher().getName());
        dto.setCategoryName(book.getCategory().getName());
        return dto;
    }
}
