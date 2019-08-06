package com.woodpecker.czq.service;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.domain.entity.Product;
import com.woodpecker.czq.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setUnit(createProductRequest.getUnit());
        product.setImageUrl(createProductRequest.getImageUrl());

        productRepository.save(product);
    }
}
