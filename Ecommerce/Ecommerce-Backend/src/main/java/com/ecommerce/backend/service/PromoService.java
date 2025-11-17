package com.ecommerce.backend.service;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.Promo;
import com.ecommerce.backend.repository.PromoRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PromoService {
    private final PromoRepository repo;
    public PromoService(PromoRepository repo) { this.repo = repo; }

    public Optional<Promo> findValid(String code) {
        return repo.findByCodeIgnoreCase(code).filter(p -> p.getValidUntil() == null || !p.getValidUntil().isBefore(LocalDate.now()));
    }
}