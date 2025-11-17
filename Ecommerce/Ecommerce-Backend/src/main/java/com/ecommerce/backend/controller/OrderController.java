package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.dto.OrderRequestDto;
import com.ecommerce.backend.dto.OrderResponseDto;
import com.ecommerce.backend.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5174")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto dto) {
        return service.createOrder(dto);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders() {
        return service.getAllOrders();
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }
}
