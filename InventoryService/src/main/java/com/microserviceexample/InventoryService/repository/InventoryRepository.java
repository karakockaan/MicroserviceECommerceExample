package com.microserviceexample.InventoryService.repository;

import com.microserviceexample.InventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductCodeIn(List<String> productCodeList);
}
