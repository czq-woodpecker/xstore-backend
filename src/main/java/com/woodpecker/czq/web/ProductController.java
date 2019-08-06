package com.woodpecker.czq.web;

import com.woodpecker.czq.contract.CreateProductRequest;
import com.woodpecker.czq.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
    }
}
