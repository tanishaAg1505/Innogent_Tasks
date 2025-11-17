package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "${app.frontend.origin}")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<Product> all() { return service.getAll(); }

    @GetMapping("/{id}")
    public Product byId(@PathVariable Long id) { return service.getById(id); }
}