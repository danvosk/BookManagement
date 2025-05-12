package com.example.bookmanagement.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherRequestDto {
    private String name;
    private String country;
}
