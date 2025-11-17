package com.ecommerce.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.OrderItemDto;
import com.ecommerce.backend.dto.OrderRequestDto;
import com.ecommerce.backend.dto.OrderResponseDto;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.OrderItem;
import com.ecommerce.backend.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    // ---------------------------------------------------
    // CREATE NEW ORDER
    // ---------------------------------------------------
    public OrderResponseDto createOrder(OrderRequestDto dto) {

        List<OrderItem> itemEntities = dto.getItems().stream()
                .map(i -> new OrderItem(
                        null,
                         i.getProductId(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .collect(Collectors.toList());

        Order order = new Order();
        order.setItems(itemEntities);
        order.setTotalAmount(dto.getTotalAmount());
        order.setAddress(dto.getAddress());
        order.setPromoCode(dto.getPromoCode());
        order.setStatus("PENDING");
        order.setPlacedAt(LocalDateTime.now());
        order.setDeliveredAt(null);

        Order saved = orderRepo.save(order);
        return mapToDto(saved);
    }

    // ---------------------------------------------------
    // GET ALL ORDERS
    // ---------------------------------------------------
    public List<OrderResponseDto> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------
    // UPDATE STATUS
    // ---------------------------------------------------
    public OrderResponseDto updateStatus(Long id, String status) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        if (status.equals("DELIVERED")) {
            order.setDeliveredAt(LocalDateTime.now());
        }

        Order updated = orderRepo.save(order);
        return mapToDto(updated);
    }

    // ---------------------------------------------------
    // SCHEDULER LOGIC
    // ---------------------------------------------------
    public void autoDeliverPendingOrders() {
        List<Order> pending = orderRepo.findByStatus("PENDING");

        for (Order order : pending) {
            if (order.getPlacedAt().isBefore(LocalDateTime.now().minusSeconds(60))) {
                order.setStatus("DELIVERED");
                order.setDeliveredAt(LocalDateTime.now());
                orderRepo.save(order);
            }
        }
    }

    // ---------------------------------------------------
    // MAP ENTITY → DTO
    // ---------------------------------------------------
    private OrderResponseDto mapToDto(Order order) {

        List<OrderItemDto> items = order.getItems().stream()
                .map(i -> new OrderItemDto(
                        i.getProductId(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderResponseDto( order.getId(),
                items,
                order.getTotalAmount(),
                order.getAddress(),
                order.getPromoCode(),
                order.getStatus(),
                order.getPlacedAt(),
                order.getDeliveredAt());
    }
}
