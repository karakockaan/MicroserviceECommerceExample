package com.microserviceexample.OrderService.service;

import com.microserviceexample.OrderService.dto.OrderRequest;
import com.microserviceexample.OrderService.model.Order;
import com.microserviceexample.OrderService.model.OrderItems;
import com.microserviceexample.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
        orderRepository.save(order);
    }
}
