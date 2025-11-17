package com.ecommerce.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
	 private List<OrderItemDto> items;
	    private Double totalAmount;
	    private String address;
	    private String promoCode;
	    
	    public OrderRequestDto() {}

	    public List<OrderItemDto> getItems() { return items; }
	    public void setItems(List<OrderItemDto> items) { this.items = items; }

	    public Double getTotalAmount() { return totalAmount; }
	    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

	    public String getAddress() { return address; }
	    public void setAddress(String address) { this.address = address; }

	    public String getPromoCode() { return promoCode; }
	    public void setPromoCode(String promoCode) { this.promoCode = promoCode; }
}