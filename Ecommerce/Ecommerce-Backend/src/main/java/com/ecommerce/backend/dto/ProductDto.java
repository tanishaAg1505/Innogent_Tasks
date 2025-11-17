package com.ecommerce.backend.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
    private Double rate;
    private Integer count;
}