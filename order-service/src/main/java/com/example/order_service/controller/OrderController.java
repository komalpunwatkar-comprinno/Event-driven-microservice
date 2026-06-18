package com.example.order_service.controller;

import com.example.order_service.event.OrderCreatedEvent;
import com.example.order_service.service.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String createOrder(
            @RequestBody OrderCreatedEvent event) {

        producer.publishOrder(event);

        return "Order Event Sent";
    }
}
