package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.BookRequestDto;
import com.example.bookmanagement.dto.response.BookResponseDto;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.entity.Shop;
import com.example.bookmanagement.entity.Publisher;
import com.example.bookmanagement.entity.Category;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.ShopRepository;
import com.example.bookmanagement.repository.PublisherRepository;
import com.example.bookmanagement.repository.CategoryRepository;
import com.example.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ShopRepository shopRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto saveBook(BookRequestDto requestDto) {
        // İlişkili nesneleri bul
        Shop shop = shopRepository.findById(requestDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + requestDto.getShopId()));
        Publisher publisher = publisherRepository.findById(requestDto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + requestDto.getPublisherId()));
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + requestDto.getCategoryId()));

        // ModelMapper sadece title, author, page gibi basit alanları maplesin
        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPage(requestDto.getPage());

        // İlişkili nesneleri manuel olarak set et
        book.setShop(shop);
        book.setPublisher(publisher);
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);

        // Geri dönüş için yine DTO'ya mapleyelim
        return modelMapper.map(savedBook, BookResponseDto.class);
    }


    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        Shop shop = shopRepository.findById(requestDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + requestDto.getShopId()));
        Publisher publisher = publisherRepository.findById(requestDto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + requestDto.getPublisherId()));
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + requestDto.getCategoryId()));

        // Alanları manuel güncelle
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPage(requestDto.getPage());
        book.setShop(shop);
        book.setPublisher(publisher);
        book.setCategory(category);

        Book updatedBook = bookRepository.save(book);
        return modelMapper.map(updatedBook, BookResponseDto.class);
    }



    @Override
    public BookResponseDto findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return modelMapper.map(book, BookResponseDto.class);
    }

    @Override
    public List<BookResponseDto> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    @Override
    public List<BookResponseDto> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author).stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }
}