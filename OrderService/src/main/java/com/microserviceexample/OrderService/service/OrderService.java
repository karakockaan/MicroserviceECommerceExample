package com.microserviceexample.OrderService.service;

import com.microserviceexample.OrderService.dto.InventoryResponse;
import com.microserviceexample.OrderService.dto.OrderRequest;
import com.microserviceexample.OrderService.model.Order;
import com.microserviceexample.OrderService.model.OrderItems;
import com.microserviceexample.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void insertOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .build();

        List<OrderItems> orderItemsList = orderRequest.getOrderItemsList().stream().map(orderItemsDto -> {
            return OrderItems.builder()
                    .orderNumber(order.getOrderNumber())
                    .productCode(orderItemsDto.getProductCode())
                    .quantity(orderItemsDto.getQuantity())
                    .price(orderItemsDto.getPrice())
                    .build();
        }).collect(Collectors.toList());

        order.setOrderItemsList(orderItemsList);

        List<String> productCodeList = orderItemsList.stream()
                .map(OrderItems::getProductCode).collect(Collectors.toList());

        //call inventory service and insert order if product is in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventoryservice/api/inventory", uriBuilder ->
                    uriBuilder.queryParam("productCodeList", productCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        //Eger tum urunler stokta varsa true donecek.
        boolean isInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if(isInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Some Products is not in stock.");
        }

    }
}
