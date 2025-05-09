package com.example.bookmanagement.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherResponseDto {
    private Long id;
    private String name;
    private String country;
}
