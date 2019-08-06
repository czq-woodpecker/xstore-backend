package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerTest extends ApiTestBase {

    @Autowired
    private ProductService productService;

    @Test
    void should_create_the_product_and_return_201_status_when_submit_valid_request_of_create_product() throws Exception {
        String createProductRequestJson = "{\"name\": \"牛奶\",\"price\": 3.2,\"imageUrl\": \"images/product/mick.jpg\"}";

        getMockMvc().perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(createProductRequestJson))
                .andExpect(status().is(201));
    }

    @Test
    void should_return_400_status_when_submit_invalid_request_of_create_product() throws Exception {
        String invalidRequestJson = "{\"price\": \"abc\", \"unit\": \"瓶\", \"imageUrl\": \"images/milk\"}";

        getMockMvc().perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(invalidRequestJson))
                .andExpect(status().is(400));
    }

    @Test
    void should_return_400_status_and_error_message_when_submit_creating_product_request_by_exist_name() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest("milk", 7.6, "瓶", "images/milk.jpg");
        productService.createProduct(createProductRequest);
        String requestJson = "{\"name\": \"milk\", \"price\": 6.6, \"unit\": \"瓶\", \"imageUrl\": \"images/milk2.jpg\"}";

        getMockMvc().perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is(400))
                .andExpect(content().string("商品名称已存在，请输入新的商品名称"));
    }
}