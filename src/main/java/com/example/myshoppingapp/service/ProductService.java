package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id) throws NoProductFoundException;

    Product addProduct(Product product);

    void deleteById(Long id) throws NoProductFoundException;
}
