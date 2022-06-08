package com.example.myshoppingapp.service.impl;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.CartItemRepository;
import com.example.myshoppingapp.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public List<CartItem> findCartItemByUserId(Long userId) {
        return cartItemRepository.findCartItemByUserId(userId);
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}
