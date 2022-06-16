package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public List<CartItem> findCartItemByUserId(Long userId) {
        return cartItemRepository.findCartItemByUserId(userId);
    }

}
