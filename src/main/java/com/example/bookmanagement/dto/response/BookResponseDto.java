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
    private Integer page;
    private String shopName;
    private String publisherName;
    private String categoryName;

}
