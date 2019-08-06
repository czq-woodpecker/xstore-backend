package com.woodpecker.czq.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerTest extends ApiTestBase {

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
}