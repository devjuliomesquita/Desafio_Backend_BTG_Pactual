package org.juliomesquita.commom.dtos;

import java.util.List;

public record OrderRequest(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemRequest> itens
) {
}
