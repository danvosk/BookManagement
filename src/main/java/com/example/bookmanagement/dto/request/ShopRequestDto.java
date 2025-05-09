package com.example.bookmanagement.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopRequestDto {
    private String name;
    private String city;
    private String country;
}
