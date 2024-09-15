package com.juliomesquita.btgpactual.order_producer.infra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.juliomesquita.commom.dtos.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order Producer", description = "Envio de pedidos.")
public interface OrderProducerControllerDoc {
    @Operation(
            operationId = "createOrder",
            summary = "Enviar um pedido para a fila.",
            description = "Enviar pedido para a fila do RabbitMq.",
            tags = {"Order Producer"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Seccessful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad Request.")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Corpo da requisição",
                    content = @Content(
                            schema = @Schema(implementation = OrderRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Pedido",
                                    summary = "Exemplo de corpo de pedido",
                                    value = OrderRequest.orderRequestExample))))
    @PostMapping(produces = {"application/json"})
    default ResponseEntity<?> createOrder(
            @Parameter(name = "OrderRequest", description = "Corpo da requisição", required = true)
            @RequestBody OrderRequest request
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
