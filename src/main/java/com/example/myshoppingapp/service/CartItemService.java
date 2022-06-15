package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> findCartItemByUserId(Long userId) {
        return cartItemRepository.findCartItemByUserId(userId);
    }

    public Product addProduct(Product product) {
        return null;
    }
}
