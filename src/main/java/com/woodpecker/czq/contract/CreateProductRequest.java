package com.woodpecker.czq.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
