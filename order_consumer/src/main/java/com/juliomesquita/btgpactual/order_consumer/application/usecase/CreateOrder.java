package com.juliomesquita.btgpactual.order_consumer.application.usecase;

import com.juliomesquita.btgpactual.order_consumer.domain.entities.OrderAggregate;
import com.juliomesquita.btgpactual.order_consumer.domain.entities.OrderItem;
import com.juliomesquita.btgpactual.order_consumer.infra.persistence.OrderRepository;
import org.juliomesquita.commom.dtos.OrderRequest;
import org.juliomesquita.commom.interfaces.BaseUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class CreateOrder implements BaseUsecase<CreateOrder.Input, CreateOrder.Output> {
    private final Logger logger = LoggerFactory.getLogger(CreateOrder.class);
    private final OrderRepository orderRepository;

    public CreateOrder(final OrderRepository orderRepository) {
        this.orderRepository = Objects.requireNonNull(orderRepository);
    }

    public record Input(OrderRequest request) {
    }

    public record Output(Long costumerId) {
    }

    @Override
    public Output execute(Input input) {
        List<OrderItem> orderItems = input.request.itens().stream()
                .map(OrderItem::new)
                .toList();
        OrderAggregate aOrder = this.orderRepository.save(OrderAggregate.create(
                input.request().codigoPedido(), input.request().codigoCliente(), orderItems));
        logger.info("Object saved: {}", aOrder);
        return new Output(aOrder.orderId());
    }
}
