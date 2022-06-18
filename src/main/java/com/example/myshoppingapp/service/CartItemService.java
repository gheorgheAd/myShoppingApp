package com.example.myshoppingapp.service;

import com.example.myshoppingapp.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;



}
