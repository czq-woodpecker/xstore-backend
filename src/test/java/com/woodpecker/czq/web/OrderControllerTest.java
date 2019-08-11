package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.AddOrderRequest;
import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.service.OrderService;
import com.woodpecker.czq.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest extends IntegrationTestBase {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

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

    @Test
    void should_return_200_status_and_a_empty_list_when_get_orders_but_do_not_add_any_order() throws Exception {
        getMockMvc().perform(get("/api/orders"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.empty()));
    }


    @Test
    void should_return_list_containing_orders_when_getOrders() throws Exception {
        productService.createProduct(new CreateProductRequest("milk", 7.6, "瓶", "images/milk.jpg"));
        productService.createProduct(new CreateProductRequest("iphone", 999.9, "台", "images/iphonex.jpg"));
        orderService.addOrUpdateOrder(new AddOrderRequest(1L));
        orderService.addOrUpdateOrder(new AddOrderRequest(2L));
        orderService.addOrUpdateOrder(new AddOrderRequest(2L));

        getMockMvc().perform(get("/api/orders"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].productName", Matchers.is("milk")))
                .andExpect(jsonPath("$[0].productPrice", Matchers.is(7.6)))
                .andExpect(jsonPath("$[0].productUnit", Matchers.is("瓶")))
                .andExpect(jsonPath("$[0].amount", Matchers.is(1)))
                .andExpect(jsonPath("$[1].orderId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].productName", Matchers.is("iphone")))
                .andExpect(jsonPath("$[1].productPrice", Matchers.is(999.9)))
                .andExpect(jsonPath("$[1].productUnit", Matchers.is("台")))
                .andExpect(jsonPath("$[1].amount", Matchers.is(2)));
    }
}