package com.juliomesquita.btgpactual.order_producer.infra.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.juliomesquita.commom.dtos.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Component
public class LoadDatabase implements CommandLineRunner {
    private final static String URL = "http://localhost:8080/orders";
    private final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LoadDatabase(
            final RestTemplate restTemplate,
            final ObjectMapper objectMapper
    ) {
        this.restTemplate = Objects.requireNonNull(restTemplate);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    @Override
    public void run(String... args) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        InputStream inputStream = TypeReference.class.getResourceAsStream("/load/loadDatabase.json");
        List<OrderRequest> orderRequestList = this.objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        orderRequestList.forEach(order -> {
            try {
                String jsonBody = this.objectMapper.writeValueAsString(order);
                HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
                this.restTemplate.exchange(URL, HttpMethod.POST, request, Void.class);
            } catch (Throwable t) {
                logger.error("Error restTemplate: {}", t.getCause(), t);
            }
        });
        logger.info("Database initialized.");
    }
}
