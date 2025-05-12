package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.request.ShopRequestDto;
import com.example.bookmanagement.dto.response.ShopResponseDto;

import java.util.List;

public interface ShopService {
    ShopResponseDto saveShop(ShopRequestDto requestDto);
    ShopResponseDto findShopById(Long id);
    ShopResponseDto updateShop(Long id, ShopRequestDto requestDto);
    List<ShopResponseDto> findAllShops();
    void deleteShop(Long id);
}