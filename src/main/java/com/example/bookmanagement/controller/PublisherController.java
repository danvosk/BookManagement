package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.request.PublisherRequestDto;
import com.example.bookmanagement.dto.response.PublisherResponseDto;
import com.example.bookmanagement.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmanagement/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDto> savePublisher(@RequestBody PublisherRequestDto requestDto) {
        return ResponseEntity.ok(publisherService.savePublisher(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> findPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> updatePublisher(@PathVariable Long id, @RequestBody PublisherRequestDto requestDto) {
        return ResponseEntity.ok(publisherService.updatePublisher(id, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> findAllPublishers() {
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}