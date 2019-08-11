package com.woodpecker.czq.service;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.contract.GetProductResponse;
import com.woodpecker.czq.domain.entity.Product;
import com.woodpecker.czq.exception.ServiceException;
import com.woodpecker.czq.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductRequest createProductRequest) {
        if (productRepository.findByName(createProductRequest.getName()).size() > 0) {
            throw new ServiceException("商品名称已存在，请输入新的商品名称");
        }
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setUnit(createProductRequest.getUnit());
        product.setImageUrl(createProductRequest.getImageUrl());

        productRepository.save(product);
    }

    public List<GetProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        ArrayList<GetProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(new GetProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getUnit(),
                    product.getImageUrl()));
        }
        return productResponses;
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }
}
