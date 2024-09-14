package com.juliomesquita.btgpactual.order_producer.infra.controller;


import com.juliomesquita.btgpactual.order_producer.infra.services.RabbitMqService;
import org.juliomesquita.commom.constants.RabbitMqConstants;
import org.juliomesquita.commom.dtos.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "orders")
public class OrderProducerController {
    private final Logger logger = LoggerFactory.getLogger(OrderProducerController.class);
    private final RabbitMqService rabbitMqService;

    public OrderProducerController(final RabbitMqService rabbitMqService) {
        this.rabbitMqService = Objects.requireNonNull(rabbitMqService);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        logger.info("Request sent: {}", request);
        this.rabbitMqService.sendMessage(RabbitMqConstants.QUEUE_ORDER, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
