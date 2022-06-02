package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping
    public List<Product> showProducts() {
        return service.findAll();
    }

    @GetMapping("/product/{id}")
    public Product showProductById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
