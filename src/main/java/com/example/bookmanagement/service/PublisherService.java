package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.PublisherRequestDto;
import com.example.bookmanagement.dto.response.PublisherResponseDto;

import java.util.List;

public interface PublisherService {
    PublisherResponseDto savePublisher(PublisherRequestDto dto);
    PublisherResponseDto findPublisherById(Long id);
    PublisherResponseDto updatePublisher(Long id,PublisherRequestDto dto);
    List<PublisherResponseDto> findAllPublisher();
    void deletePublisher(Long id);
}
