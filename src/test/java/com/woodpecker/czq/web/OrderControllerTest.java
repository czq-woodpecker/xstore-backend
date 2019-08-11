package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends IntegrationTestBase {
    @Autowired
    private ProductService productService;

    @Test
    void should_return_201_status_when_add_order_with_valid_productId() throws Exception {
        productService.createProduct(new CreateProductRequest("milk", 7.6, "瓶", "images/milk.jpg"));
        String validProductIdJson = "{\"productId\": 1}";

        getMockMvc().perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(validProductIdJson))
                .andExpect(status().is(201));
    }

    @Test
    void should_return_400_status_when_add_order_with_invalid_productId() throws Exception {
        String inValidProductIdJson = "{\"productId\": 1}";

        getMockMvc().perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(inValidProductIdJson))
                .andExpect(status().is(400))
                .andExpect(content().string("not exist productId: 1"));
    }
}