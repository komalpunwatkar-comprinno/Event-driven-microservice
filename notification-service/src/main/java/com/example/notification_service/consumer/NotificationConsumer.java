package com.example.notification_service.consumer;

import com.example.notification_service.event.FoodPreparedEvent;
import io.micrometer.tracing.Tracer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationConsumer {
    private static final Logger log =
            LoggerFactory.getLogger(NotificationConsumer.class);

    private final Tracer tracer;

    public NotificationConsumer(Tracer tracer) {
        this.tracer = tracer;
    }
    @KafkaListener(
            topics = "food-prepared-topic",
            groupId = "notification-group"
    )
    public void consume(FoodPreparedEvent event) {
        if (tracer.currentSpan() != null) {
            System.out.println(
                    "Trace = " +
                            tracer.currentSpan().context().traceId()
            );
        } else {
            System.out.println("Trace = NULL");
        }

        log.info(
                "Notification sent for Order {} : {}",
                event.getOrderId(),
                event.getItemName()
        );
    }
}
