package com.juliomesquita.btgpactual.order_consumer.infra.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliomesquita.btgpactual.order_consumer.application.usecase.CreateOrder;
import org.juliomesquita.commom.constants.RabbitMqConstants;
import org.juliomesquita.commom.dtos.OrderRequest;
import org.juliomesquita.commom.interfaces.RabbitMqConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class OrderConsumer implements RabbitMqConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
    private final ObjectMapper objectMapper;
    private final CreateOrder createOrder;

    public OrderConsumer(
            final ObjectMapper objectMapper,
            final CreateOrder createOrder
    ) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.createOrder = Objects.requireNonNull(createOrder);
    }

    @Override
    @RabbitListener(queues = RabbitMqConstants.QUEUE_ORDER)
    public void consumer(String msg) throws Exception {
        logger.info("Message consumed: {}", msg);
        OrderRequest orderRequest = this.objectMapper.readValue(msg, OrderRequest.class);
        this.createOrder.execute(new CreateOrder.Input(orderRequest));
    }
}
