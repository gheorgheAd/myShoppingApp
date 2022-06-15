package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
}
