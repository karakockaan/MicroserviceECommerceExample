package com.microserviceexample.InventoryService.service;

import com.microserviceexample.InventoryService.dto.InventoryResponse;
import com.microserviceexample.InventoryService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> productCodeList){
        return inventoryRepository.findByProductCodeIn(productCodeList)
                .stream()
                .map(inventory -> {
                    return InventoryResponse.builder()
                            .productCode(inventory.getProductCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build();
                }).collect(Collectors.toList());
    }
}
