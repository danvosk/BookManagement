package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.dto.request.ShopRequestDto;
import com.example.bookmanagement.dto.response.ShopResponseDto;
import com.example.bookmanagement.entity.Shop;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.ShopRepository;
import com.example.bookmanagement.service.ShopService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    @Override
    public ShopResponseDto saveShop(ShopRequestDto dto) {
        Shop shop = modelMapper.map(dto, Shop.class);
        Shop saved = shopRepository.save(shop);
        return convertToDto(saved);
    }

    @Override
    public ShopResponseDto findShopById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + id));
        return convertToDto(shop);
    }

    @Override
    public ShopResponseDto updateShop(Long id, ShopRequestDto dto) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + id));
        shop.setName(dto.getName());
        shop.setCity(dto.getCity());
        shop.setCountry(dto.getCountry());
        Shop updated = shopRepository.save(shop);
        return convertToDto(updated);
    }

    @Override
    public List<ShopResponseDto> findAllShops() {
        return shopRepository.findAll()
                .stream()
                .map(shop -> convertToDto(shop))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }

    private ShopResponseDto convertToDto(Shop shop) {
        return modelMapper.map(shop, ShopResponseDto.class);
    }

}
