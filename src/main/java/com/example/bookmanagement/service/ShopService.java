package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.ShopRequestDto;
import com.example.bookmanagement.dto.response.ShopResponseDto;

import java.util.List;

public interface ShopService {
    ShopResponseDto saveShop(ShopRequestDto dto);
    ShopResponseDto updateShop(Long id, ShopRequestDto dto);
    ShopResponseDto findShopById(Long id);
    List<ShopResponseDto> findAllShops();
    void deleteShop(Long id);
}
