package com.example.myshoppingapp.service.impl;

import com.example.myshoppingapp.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



}
