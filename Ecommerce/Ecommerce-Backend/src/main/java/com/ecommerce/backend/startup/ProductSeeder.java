package com.ecommerce.backend.startup;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductSeeder {

    private final ProductRepository productRepo;

    public ProductSeeder(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @PostConstruct   // runs automatically when project starts
    public void seedProducts() {

        if (productRepo.count() == 0) {
            System.out.println("Seeding products from Fake Store API");

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://fakestoreapi.com/products";

            Product[] products = restTemplate.getForObject(url, Product[].class);

            if (products != null) {
                for (Product p : products) {
                    productRepo.save(p);
                }
            }

            System.out.println("Seeding complete!");
        }
    }
}
