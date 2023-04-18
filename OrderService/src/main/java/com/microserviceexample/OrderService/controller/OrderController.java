package com.microserviceexample.OrderService.controller;

import com.microserviceexample.OrderService.dto.OrderRequest;
import com.microserviceexample.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    String insertOrder(@RequestBody OrderRequest orderRequest){
        orderService.insertOrder(orderRequest);
        return "Order Placed Successfully.";
    }
}
