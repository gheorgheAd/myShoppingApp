package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);
}
