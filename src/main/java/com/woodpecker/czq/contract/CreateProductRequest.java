package com.woodpecker.czq.contract;

import lombok.Data;

@Data
public class CreateProductRequest {
    private String name;
    private Double price;
    private String unit;
    private String imageUrl;
}
