package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product addProduct(Product product);
}
