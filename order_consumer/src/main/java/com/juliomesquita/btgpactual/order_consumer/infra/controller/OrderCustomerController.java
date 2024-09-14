package com.juliomesquita.btgpactual.order_consumer.infra.controller;

import com.juliomesquita.btgpactual.order_consumer.application.usecase.FindOrderByCustomer;
import org.juliomesquita.commom.dtos.ApiResponseGeneric;
import org.juliomesquita.commom.dtos.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "order-customer")
public class OrderCustomerController {
    private final Logger logger = LoggerFactory.getLogger(OrderCustomerController.class);
    private final FindOrderByCustomer findOrderByCustomer;

    public OrderCustomerController(final FindOrderByCustomer findOrderByCustomer) {
        this.findOrderByCustomer = Objects.requireNonNull(findOrderByCustomer);
    }

    @GetMapping(value = "/{customerId}/orders")
    public ResponseEntity<ApiResponseGeneric<OrderResponse>> findOrderByCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", defaultValue = "20") Integer perPage
    ){
        try{
            FindOrderByCustomer.Output response =
                    this.findOrderByCustomer.execute(new FindOrderByCustomer.Input(customerId, page, perPage));

            return ResponseEntity.ok(response.pageOrder());
        } catch (Throwable t){
            return ResponseEntity.notFound().build();
        }

    }
}
