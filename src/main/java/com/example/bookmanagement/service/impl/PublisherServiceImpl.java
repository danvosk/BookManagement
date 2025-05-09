package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.entity.Publisher;
import com.example.bookmanagement.dto.request.PublisherRequestDto;
import com.example.bookmanagement.dto.response.PublisherResponseDto;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.PublisherRepository;
import com.example.bookmanagement.service.PublisherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Override
    public PublisherResponseDto savePublisher(PublisherRequestDto dto) {
        Publisher publisher = modelMapper.map(dto, Publisher.class);
        Publisher saved = publisherRepository.save(publisher);
        return convertToDto(saved);
    }

    @Override
    public PublisherResponseDto findPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " +id ));

        return convertToDto(publisher);
    }

    @Override
    public PublisherResponseDto updatePublisher(Long id, PublisherRequestDto dto) {

        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " +id ));

        publisher.setName(dto.getName());
        publisher.setCountry(dto.getCountry());

        Publisher updated = publisherRepository.save(publisher);

        return convertToDto(updated);
    }

    @Override
    public List<PublisherResponseDto> findAllPublisher() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> convertToDto(publisher))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    private PublisherResponseDto convertToDto(Publisher publisher) {
        return modelMapper.map(publisher, PublisherResponseDto.class);
    }

}
