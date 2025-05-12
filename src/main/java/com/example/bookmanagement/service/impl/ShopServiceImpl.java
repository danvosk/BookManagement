package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.ShopRequestDto;
import com.example.bookmanagement.dto.response.ShopResponseDto;
import com.example.bookmanagement.entity.Shop;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.ShopRepository;
import com.example.bookmanagement.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShopResponseDto saveShop(ShopRequestDto requestDto) {
        Shop shop = modelMapper.map(requestDto, Shop.class);
        shop = shopRepository.save(shop);
        return modelMapper.map(shop, ShopResponseDto.class);
    }

    @Override
    public ShopResponseDto findShopById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + id));
        return modelMapper.map(shop, ShopResponseDto.class);
    }

    @Override
    public ShopResponseDto updateShop(Long id, ShopRequestDto requestDto) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + id));
        modelMapper.map(requestDto, shop);
        shop = shopRepository.save(shop);
        return modelMapper.map(shop, ShopResponseDto.class);
    }

    @Override
    public List<ShopResponseDto> findAllShops() {
        return shopRepository.findAll().stream()
                .map(shop -> modelMapper.map(shop, ShopResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + id));
        shopRepository.delete(shop);
    }
}