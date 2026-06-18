package com.example.order_service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOrderShouldReturnSuccess() throws Exception {

        String requestBody = """
                {
                  "orderId": 1,
                  "itemName": "Pizza"
                }
                """;

        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Order Event Sent"));
    }
}
