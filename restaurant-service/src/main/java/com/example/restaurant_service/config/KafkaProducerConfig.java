package com.example.restaurant_service.config;
import com.example.restaurant_service.event.FoodPreparedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import  com.example.restaurant_service.event.FoodPreparedEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, FoodPreparedEvent> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class
        );

        config.put(
                JsonSerializer.ADD_TYPE_INFO_HEADERS,
                false
        );

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, FoodPreparedEvent> kafkaTemplate() {
        KafkaTemplate<String, FoodPreparedEvent> template =
                new KafkaTemplate<>(producerFactory());

        template.setObservationEnabled(true);

        return template;
    }
}
