package com.warehouse.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.api.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetProduct() throws Exception {
        ProductDto product = new ProductDto(null, "Monitor", "4K Display", new BigDecimal("250.00"), 15);

        // Create product
        String json = objectMapper.writeValueAsString(product);

        String response = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Monitor"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDto responseDto = objectMapper.readValue(response, ProductDto.class);

        // Get product by ID
        mockMvc.perform(get("/api/products/" + responseDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Monitor"));
    }
}
