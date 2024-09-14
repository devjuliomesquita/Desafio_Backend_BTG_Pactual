package com.juliomesquita.btgpactual.order_consumer.application.usecase;

import com.juliomesquita.btgpactual.order_consumer.domain.entities.OrderAggregate;
import com.juliomesquita.btgpactual.order_consumer.infra.persistence.OrderRepository;
import org.juliomesquita.commom.dtos.ApiResponseGeneric;
import org.juliomesquita.commom.dtos.OrderResponse;
import org.juliomesquita.commom.dtos.PaginationGeneric;
import org.juliomesquita.commom.interfaces.BaseUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class FindOrderByCustomer implements BaseUsecase<FindOrderByCustomer.Input, FindOrderByCustomer.Output> {
    private final Logger logger = LoggerFactory.getLogger(FindOrderByCustomer.class);
    private final OrderRepository orderRepository;

    public FindOrderByCustomer(
            final OrderRepository orderRepository
    ) {
        this.orderRepository = Objects.requireNonNull(orderRepository);
    }

    public record Input(Long customerId, Integer page, Integer perPage) {
    }

    public record Output(ApiResponseGeneric<OrderResponse> pageOrder) {
        public static PaginationGeneric restore(Page<?> page) {
            return PaginationGeneric.builder()
                    .page(page.getNumber())
                    .perPage(page.getSize())
                    .totalPages(page.getTotalPages())
                    .totalElements(page.getTotalElements())
                    .build();
        }
    }

    @Override
    public Output execute(Input input) {
        PageRequest pageRequest = PageRequest.of(input.page, input.perPage);
        Page<OrderResponse> orderResponses =
                this.orderRepository.findAllByCustomerId(input.customerId, pageRequest)
                        .map(OrderAggregate::restore);
        BigDecimal totalOrders = this.orderRepository.findTotalOrders(input.customerId);

        logger.info("Response sent: {}", input);
        return new Output(new ApiResponseGeneric<>(
                Map.of("Total orders by customer", totalOrders),
                orderResponses.getContent(),
                Output.restore(orderResponses)
        ));
    }
}
