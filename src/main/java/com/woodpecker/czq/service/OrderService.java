package com.woodpecker.czq.service;

import com.woodpecker.czq.contract.AddOrderRequest;
import com.woodpecker.czq.contract.GetOrderResponse;
import com.woodpecker.czq.domain.entity.Order;
import com.woodpecker.czq.domain.entity.Product;
import com.woodpecker.czq.exception.NotExistException;
import com.woodpecker.czq.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.naming.directory.NoSuchAttributeException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GetOrderResponse> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    return new GetOrderResponse(
                            order.getId(),
                            order.getProduct().getName(),
                            order.getProduct().getPrice(),
                            order.getProduct().getUnit(),
                            order.getAmount()
                    );
                }).collect(Collectors.toList());
    }

    public void deleteOrder(Long orderId) {
        if (orderId != null && orderRepository.findById(orderId).isPresent()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NotExistException("not exist orderId: " + orderId);
        }
    }
}
