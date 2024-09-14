package org.juliomesquita.commom.dtos;

import java.math.BigDecimal;

public record OrderItemRequest(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
