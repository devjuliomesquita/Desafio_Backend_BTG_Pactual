package org.juliomesquita.commom.dtos;

import java.util.List;

public record OrderRequest(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemRequest> itens
) {
    public static final String orderRequestExample = "{\n" +
            "  \"codigoPedido\": 100,\n" +
            "  \"codigoCliente\": 2,\n" +
            "  \"itens\": [\n" +
            "    {\n" +
            "      \"produto\": \"Produto 1\",\n" +
            "      \"quantidade\": 2,\n" +
            "      \"preco\": 100.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"produto\": \"Produto 2\",\n" +
            "      \"quantidade\": 1,\n" +
            "      \"preco\": 50.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
