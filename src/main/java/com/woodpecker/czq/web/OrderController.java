package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.AddOrderRequest;
import com.woodpecker.czq.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateOrder(@RequestBody @Valid AddOrderRequest addOrderRequest) {
        orderService.addOrUpdateOrder(addOrderRequest);
    }
}
