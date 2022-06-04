package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping("/products")
    public List<Product> showProducts() {
        return service.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> showProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return ResponseEntity.ok(product);
    }
}
