package com.microserviceexample.discoveryserver.controller;

import com.microserviceexample.discoveryserver.dto.InventoryResponse;
import com.microserviceexample.discoveryserver.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> productCodeList){
        return inventoryService.isInStock(productCodeList);
    }
}
