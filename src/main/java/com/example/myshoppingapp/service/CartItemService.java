package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.model.Product;

import java.util.List;

public interface CartItemService {
    List<CartItem> findCartItemByUserId(Long userId);

    Product addProduct(Product product);
}
