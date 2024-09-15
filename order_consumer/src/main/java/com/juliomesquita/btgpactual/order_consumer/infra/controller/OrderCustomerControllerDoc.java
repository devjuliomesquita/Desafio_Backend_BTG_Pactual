package com.juliomesquita.btgpactual.order_consumer.infra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.juliomesquita.commom.dtos.ApiResponseGeneric;
import org.juliomesquita.commom.dtos.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Order Customer", description = "Busca de pedidos.")
public interface OrderCustomerControllerDoc {
    @Operation(
            operationId = "findOrderByCustomer",
            summary = "Buscar pedidos por cliente.",
            description = "Buscar pedidos por cliente.",
            tags = {"Order Customer"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseGeneric.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request."),
                    @ApiResponse(responseCode = "404", description = "Not Found.")})
    @GetMapping(value = {"/{customerId}/orders"}, produces = {"application/json"})
    default ResponseEntity<ApiResponseGeneric<OrderResponse>> findOrderByCustomer(
            @PathVariable(value = "customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", defaultValue = "20") Integer perPage
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
