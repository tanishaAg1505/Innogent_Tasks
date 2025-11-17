package com.ecommerce.backend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderSchedulerService {

    private final OrderService orderService;

    public OrderSchedulerService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 30000)
    public void runScheduler() {
        orderService.autoDeliverPendingOrders();
    }
}