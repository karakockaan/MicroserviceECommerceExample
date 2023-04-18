package com.microserviceexample.InventoryService.service;

import com.microserviceexample.InventoryService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String productCode){
        return inventoryRepository.findByProductCode(productCode).isPresent();
    }
}
