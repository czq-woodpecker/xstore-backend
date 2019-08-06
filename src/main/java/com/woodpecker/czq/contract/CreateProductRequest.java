package com.woodpecker.czq.contract;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class CreateProductRequest {
    @Length(min = 1, max = 128)
    private String name;
    @NotNull
    private Double price;
    @Length(min = 1, max = 10)
    private String unit;
    @Length(min = 1, max = 128)
    private String imageUrl;
}
