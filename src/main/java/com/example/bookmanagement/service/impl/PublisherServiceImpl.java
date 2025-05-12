package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.PublisherRequestDto;
import com.example.bookmanagement.dto.response.PublisherResponseDto;
import com.example.bookmanagement.entity.Publisher;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.PublisherRepository;
import com.example.bookmanagement.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, ModelMapper modelMapper) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PublisherResponseDto savePublisher(PublisherRequestDto requestDto) {
        Publisher publisher = modelMapper.map(requestDto, Publisher.class);
        publisher = publisherRepository.save(publisher);
        return modelMapper.map(publisher, PublisherResponseDto.class);
    }

    @Override
    public PublisherResponseDto findPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        return modelMapper.map(publisher, PublisherResponseDto.class);
    }

    @Override
    public PublisherResponseDto updatePublisher(Long id, PublisherRequestDto requestDto) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        modelMapper.map(requestDto, publisher);
        publisher = publisherRepository.save(publisher);
        return modelMapper.map(publisher, PublisherResponseDto.class);
    }

    @Override
    public List<PublisherResponseDto> findAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisher -> modelMapper.map(publisher, PublisherResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        publisherRepository.delete(publisher);
    }
}