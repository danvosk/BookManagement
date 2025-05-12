package com.example.bookmanagement.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private Long page;
    private Long shopId;
    private Long publisherId;
    private Long categoryId;
}