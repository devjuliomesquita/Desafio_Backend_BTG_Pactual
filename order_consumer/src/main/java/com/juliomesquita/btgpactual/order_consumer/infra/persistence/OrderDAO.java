package com.juliomesquita.btgpactual.order_consumer.infra.persistence;

import java.math.BigDecimal;

public interface OrderDAO {
    BigDecimal findTotalOrders(Long customerId);
}
