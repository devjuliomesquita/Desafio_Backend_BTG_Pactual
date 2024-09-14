package org.juliomesquita.commom.interfaces;

public interface RabbitMqConsumer {
    void consumer(String msg) throws Exception;
}
