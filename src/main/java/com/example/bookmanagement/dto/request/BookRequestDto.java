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
    private int publicationYear;
}
