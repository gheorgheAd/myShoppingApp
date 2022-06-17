package com.example.myshoppingapp.service;

import com.example.myshoppingapp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    OrderRepository orderRepository;

}
