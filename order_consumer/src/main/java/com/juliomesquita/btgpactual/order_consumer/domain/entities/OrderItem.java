package com.juliomesquita.btgpactual.order_consumer.domain.entities;

import org.juliomesquita.commom.dtos.OrderItemRequest;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

public record OrderItem(
        String productName,
        Integer qnt,
        @Field(targetType = FieldType.DECIMAL128) BigDecimal price
) {
    public OrderItem(OrderItemRequest orderItemRequest) {
        this(orderItemRequest.produto(), orderItemRequest.quantidade(), orderItemRequest.preco());
    }
}
