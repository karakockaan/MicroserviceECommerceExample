package com.microserviceexample.OrderService.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.PERSIST)
    private transient List<OrderItems> orderItemsList;
}
