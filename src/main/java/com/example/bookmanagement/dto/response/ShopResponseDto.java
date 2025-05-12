package com.example.bookmanagement.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopResponseDto {
    private Long id;
    private String name;
    private String city;
    private String country;
}
