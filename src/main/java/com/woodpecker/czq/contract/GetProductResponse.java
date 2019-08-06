package com.woodpecker.czq.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetProductResponse {
    private String name;
    private Double price;
    private String unit;
    private String imageUrl;
}
