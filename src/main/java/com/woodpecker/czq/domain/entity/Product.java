package com.woodpecker.czq.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 128)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 10)
    private String unit;
    @Column(nullable = false, length = 128)
    private String imageUrl;
}
