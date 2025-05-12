package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.request.ShopRequestDto;
import com.example.bookmanagement.dto.response.ShopResponseDto;
import com.example.bookmanagement.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmanagement/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<ShopResponseDto> saveShop(@RequestBody ShopRequestDto requestDto) {
        return ResponseEntity.ok(shopService.saveShop(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResponseDto> findShopById(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findShopById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopResponseDto> updateShop(@PathVariable Long id, @RequestBody ShopRequestDto requestDto) {
        return ResponseEntity.ok(shopService.updateShop(id, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ShopResponseDto>> findAllShops() {
        return ResponseEntity.ok(shopService.findAllShops());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }
}