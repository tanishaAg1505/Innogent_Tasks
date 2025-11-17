package com.ecommerce.backend.dto;

import lombok.Data;

@Data
public class PromoDto {
    private String code;
    private String type;
    private Double amount;
}