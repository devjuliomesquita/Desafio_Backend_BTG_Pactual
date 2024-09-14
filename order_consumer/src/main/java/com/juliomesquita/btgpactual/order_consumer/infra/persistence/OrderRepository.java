package com.juliomesquita.btgpactual.order_consumer.infra.persistence;

import com.juliomesquita.btgpactual.order_consumer.domain.entities.OrderAggregate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderAggregate, Long>, OrderDAO {
    Page<OrderAggregate> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
