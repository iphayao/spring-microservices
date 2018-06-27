package com.example.book.repository;

import com.example.book.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
