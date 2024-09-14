package com.juliomesquita.btgpactual.order_consumer.domain.entities;

import org.juliomesquita.commom.dtos.OrderResponse;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "tb_orders")
public record OrderAggregate(
        @MongoId Long orderId,
        @Indexed(name = "customer_id_index") Long customerId,
        @Field(targetType = FieldType.DECIMAL128) BigDecimal total,
        List<OrderItem> items
) {
    public static OrderAggregate create(Long orderId, Long customerId, List<OrderItem> items) {
        BigDecimal total = items.stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.qnt())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return new OrderAggregate(orderId, customerId, total, items);
    }

    public static OrderResponse restore(OrderAggregate orderAggregate){
        return new OrderResponse(orderAggregate.orderId, orderAggregate.customerId, orderAggregate.total);
    }
}
