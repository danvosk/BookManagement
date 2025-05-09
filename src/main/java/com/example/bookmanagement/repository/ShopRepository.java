package com.example.bookmanagement.repository;

import com.example.bookmanagement.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
