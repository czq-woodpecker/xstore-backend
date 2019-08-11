package com.woodpecker.czq.service;

import com.woodpecker.czq.contract.AddOrderRequest;
import com.woodpecker.czq.domain.entity.Order;
import com.woodpecker.czq.domain.entity.Product;
import com.woodpecker.czq.exception.NotExistException;
import com.woodpecker.czq.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.naming.directory.NoSuchAttributeException;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void addOrUpdateOrder(AddOrderRequest addOrderRequest) {
        Optional<Product> product = productService.findById(addOrderRequest.getProductId());
        if (product.isPresent()) {
            Optional<Order> optionalOrder = orderRepository.findByProductId(addOrderRequest.getProductId());
            Order order;
            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
                order.setAmount(order.getAmount() + 1);
            } else {
                order = new Order();
                order.setAmount(1);
                order.setProduct(product.get());
            }
            orderRepository.save(order);
        } else {
            throw new NotExistException("not exist productId: " + addOrderRequest.getProductId());
        }
    }
}
