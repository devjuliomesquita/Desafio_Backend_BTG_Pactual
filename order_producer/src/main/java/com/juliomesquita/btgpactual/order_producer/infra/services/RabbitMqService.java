package com.juliomesquita.btgpactual.order_producer.infra.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RabbitMqService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMqService(
            final RabbitTemplate rabbitTemplate,
            final ObjectMapper objectMapper
            ) {
        this.rabbitTemplate = Objects.requireNonNull(rabbitTemplate);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public void sendMessage(String routingKey, Object message){
        try {
            String messageJson = this.objectMapper.writeValueAsString(message);
            this.rabbitTemplate.convertAndSend(routingKey, messageJson);
        } catch (Throwable t){
            t.printStackTrace();
        }
    }
}
