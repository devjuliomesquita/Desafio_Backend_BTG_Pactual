package com.juliomesquita.btgpactual.order_producer.infra.connection;


import org.juliomesquita.commom.constants.RabbitMqConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RabbitMqConnection {
    private static final String EXCHANGE_NAME = "amq.direct";
    private final AmqpAdmin amqpAdmin;

    public RabbitMqConnection(final AmqpAdmin amqpAdmin) {
        this.amqpAdmin = Objects.requireNonNull(amqpAdmin);
        this.add();
    }

    private Queue queue(String nameQueue){
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    private void add(){
        Queue queueOrder = this.queue(RabbitMqConstants.QUEUE_ORDER);
        DirectExchange directExchange = this.directExchange();
        Binding bindingOrder = this.binding(queueOrder, directExchange);

        this.amqpAdmin.declareQueue(queueOrder);
        this.amqpAdmin.declareExchange(directExchange);
        this.amqpAdmin.declareBinding(bindingOrder);
    }
}
