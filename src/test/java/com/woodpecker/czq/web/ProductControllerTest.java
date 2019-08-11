package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProductControllerTest extends IntegrationTestBase {

    @Autowired
    private ProductService productService;

    @Test
    void should_create_the_product_and_return_201_status_when_submit_valid_request_of_create_product() throws Exception {
        String createProductRequestJson = "{\"name\": \"牛奶\",\"price\": 3.2,\"imageUrl\": \"images/product/mick.jpg\"}";

        getMockMvc().perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(createProductRequestJson))
                .andExpect(status().is(201));
    }

    @Test
    void should_return_400_status_when_submit_invalid_request_of_create_product() throws Exception {
        String invalidRequestJson = "{\"price\": \"abc\", \"unit\": \"瓶\", \"imageUrl\": \"images/milk\"}";

        getMockMvc().perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(invalidRequestJson))
                .andExpect(status().is(400));
    }

    @Test
    void should_return_400_status_and_error_message_when_submit_creating_product_request_by_exist_name() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest("milk", 7.6, "瓶", "images/milk.jpg");
        productService.createProduct(createProductRequest);
        String requestJson = "{\"name\": \"milk\", \"price\": 6.6, \"unit\": \"瓶\", \"imageUrl\": \"images/milk2.jpg\"}";

        getMockMvc().perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is(400))
                .andExpect(content().string("商品名称已存在，请输入新的商品名称"));
    }

    @Test
    void should_return_products_when_get_products() throws Exception {
        CreateProductRequest productRequest1 = new CreateProductRequest("milk", 7.6, "瓶", "images/milk.jpg");
        CreateProductRequest productRequest2 = new CreateProductRequest("apple", 9.9, "个", "images/apple.jpg");
        productService.createProduct(productRequest1);
        productService.createProduct(productRequest2);

        getMockMvc().perform(get("/api/products"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].name", Matchers.is("milk")))
                .andExpect(jsonPath("$[0].price", Matchers.is(7.6)))
                .andExpect(jsonPath("$[0].unit", Matchers.is("瓶")))
                .andExpect(jsonPath("$[0].imageUrl", Matchers.is("images/milk.jpg")))
                .andExpect(jsonPath("$[1].name", Matchers.is("apple")))
                .andExpect(jsonPath("$[1].price", Matchers.is(9.9)))
                .andExpect(jsonPath("$[1].unit", Matchers.is("个")))
                .andExpect(jsonPath("$[1].imageUrl", Matchers.is("images/apple.jpg")));
    }

    @Test
    void should_return_empty_list_when_get_products_but_do_not_add_any_product() throws Exception {
        getMockMvc().perform(get("/api/products"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.empty()));
    }
}