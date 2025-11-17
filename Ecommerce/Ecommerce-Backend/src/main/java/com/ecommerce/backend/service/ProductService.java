package com.ecommerce.backend.service;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}