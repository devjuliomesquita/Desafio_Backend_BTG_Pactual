package com.juliomesquita.btgpactual.order_consumer.infra.persistence;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Component
public class OrderDAOImpl implements OrderDAO {
    private final MongoTemplate mongoTemplate;

    public OrderDAOImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = Objects.requireNonNull(mongoTemplate);
    }

    @Override
    public BigDecimal findTotalOrders(Long customerId) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );
        AggregationResults<Document> total = this.mongoTemplate.aggregate(aggregation, "tb_orders", Document.class);
        return new BigDecimal(Objects.requireNonNull(total.getUniqueMappedResult()).get("total").toString());
    }
}
