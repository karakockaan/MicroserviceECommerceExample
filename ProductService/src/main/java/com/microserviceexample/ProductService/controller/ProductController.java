package com.microserviceexample.ProductService.controller;

import com.microserviceexample.ProductService.dto.ProductRequest;
import com.microserviceexample.ProductService.dto.ProductResponse;
import com.microserviceexample.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody ProductRequest productRequest) {
        productService.insertProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
