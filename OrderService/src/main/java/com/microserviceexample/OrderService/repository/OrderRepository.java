package com.microserviceexample.OrderService.repository;

import com.microserviceexample.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
