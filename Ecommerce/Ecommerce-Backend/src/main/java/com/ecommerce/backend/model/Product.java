package com.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // keep same as Fake API

    private String title;
    private Double price;

    @Column(length = 2000)
    private String description;

    private String category;
    private String image;

    private Double rate;
    private Integer count;
}