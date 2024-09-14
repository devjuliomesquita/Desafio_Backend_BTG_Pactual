package com.juliomesquita.btgpactual.order_consumer.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliomesquita.btgpactual.order_consumer.application.usecase.CreateOrder;
import com.juliomesquita.btgpactual.order_consumer.application.usecase.FindOrderByCustomer;
import com.juliomesquita.btgpactual.order_consumer.infra.persistence.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class BeansConfig {
    private final OrderRepository orderRepository;

    public BeansConfig(
            final OrderRepository orderRepository
    ) {
        this.orderRepository = Objects.requireNonNull(orderRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CreateOrder createOrder() {
        return new CreateOrder(orderRepository);
    }

    @Bean
    public FindOrderByCustomer findOrderByCustomer() {
        return new FindOrderByCustomer(orderRepository);
    }

}
