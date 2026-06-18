package com.example.order_service.service;

import com.example.order_service.event.OrderCreatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderProducerTest {
    @Mock
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @InjectMocks
    private OrderProducer orderProducer;

    @Test
    void shouldPublishOrderEvent() {

        OrderCreatedEvent event = new OrderCreatedEvent();

        event.setOrderId(1L);
        event.setItemName("Pizza");

        orderProducer.publishOrder(event);

        verify(kafkaTemplate)
                .send("order-created-topic-v4", event);
    }
}
