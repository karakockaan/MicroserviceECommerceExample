package com.microserviceexample.discoveryserver.repository;

import com.microserviceexample.discoveryserver.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductCodeIn(List<String> productCodeList);
}
