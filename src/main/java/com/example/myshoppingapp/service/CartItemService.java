package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NotEnoughProductsInStockException;
import com.example.myshoppingapp.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface CartItemService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();

}
