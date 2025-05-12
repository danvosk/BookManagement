package com.example.bookmanagement.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {
    private String title;
    private String author;
    private Long page;
    private Long shopId;
    private Long publisherId;
    private Long categoryId;
}