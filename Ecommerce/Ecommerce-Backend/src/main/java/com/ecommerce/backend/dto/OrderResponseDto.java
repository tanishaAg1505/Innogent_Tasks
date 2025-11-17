package com.ecommerce.backend.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
	private Long id;
    private List<OrderItemDto> items;
    private Double totalAmount;
    private String address;
    private String promoCode;
    private String status;
    private LocalDateTime placedAt;
    private LocalDateTime deliveredAt;
    public OrderResponseDto() {}

    public OrderResponseDto(Long id, List<OrderItemDto> items, Double totalAmount,
                            String address, String promoCode, String status,
                            LocalDateTime placedAt, LocalDateTime deliveredAt) {
        this.id = id;
        this.items = items;
        this.totalAmount = totalAmount;
        this.address = address;
        this.promoCode = promoCode;
        this.status = status;
        this.placedAt = placedAt;
        this.deliveredAt = deliveredAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<OrderItemDto> getItems() { return items; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPromoCode() { return promoCode; }
    public void setPromoCode(String promoCode) { this.promoCode = promoCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPlacedAt() { return placedAt; }
    public void setPlacedAt(LocalDateTime placedAt) { this.placedAt = placedAt; }

    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
}