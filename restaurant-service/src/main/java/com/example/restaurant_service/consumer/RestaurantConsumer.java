package com.example.restaurant_service.consumer;

import com.example.restaurant_service.event.FoodPreparedEvent;
import com.example.restaurant_service.event.OrderCreatedEvent;
import io.micrometer.tracing.Tracer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RestaurantConsumer {
    private static final Logger log =
            LoggerFactory.getLogger(RestaurantConsumer.class);

    private final KafkaTemplate<String, FoodPreparedEvent> kafkaTemplate;
    private final Tracer tracer;

    public RestaurantConsumer(
            KafkaTemplate<String, FoodPreparedEvent> kafkaTemplate, Tracer tracer ) {
        this.kafkaTemplate = kafkaTemplate;
        this.tracer = tracer;
    }

    @KafkaListener(
            topics = "order-created-topic-v4",
            groupId = "restaurant-group"
    )
    public void consume(OrderCreatedEvent event) {
        System.out.println(
                "TRACE ID = "
                        + tracer.currentSpan().context().traceId()
        );

        log.info(
                "Restaurant received order: {} {}",
                event.getOrderId(),
                event.getItemName()
        );

        FoodPreparedEvent preparedEvent = new FoodPreparedEvent();

        preparedEvent.setOrderId(event.getOrderId());
        preparedEvent.setItemName(event.getItemName());

        kafkaTemplate.send(
                "food-prepared-topic",
                preparedEvent
        );

        log.info("Food prepared");
    }
}