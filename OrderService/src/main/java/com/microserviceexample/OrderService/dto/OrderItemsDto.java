package com.microserviceexample.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {
    private Long id;
    private String orderNumber;
    private String productCode;
    private Integer quantity;
    private BigDecimal price;
}
