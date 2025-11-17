package com.ecommerce.backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.model.Promo;
import com.ecommerce.backend.service.PromoService;

@RestController
@RequestMapping("/api/promos")
@CrossOrigin(origins = "${app.frontend.origin}")
public class PromoController {
    private final PromoService service;
    public PromoController(PromoService service) { this.service = service; }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String code) {
        Optional<Promo> promo = service.findValid(code);

        if (promo.isPresent()) {
            return ResponseEntity.ok(promo.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid promo code");
        }
    }
}