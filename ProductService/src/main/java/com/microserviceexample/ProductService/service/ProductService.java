package com.microserviceexample.ProductService.service;

import com.microserviceexample.ProductService.dto.ProductResponse;
import com.microserviceexample.ProductService.model.Product;
import com.microserviceexample.ProductService.dto.ProductRequest;
import com.microserviceexample.ProductService.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void insertProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .detail(productRequest.getDetail())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product is saved. {}", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responseList = products.stream().map(product -> {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .detail(product.getDetail())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toList());
        return responseList;
    }
}
