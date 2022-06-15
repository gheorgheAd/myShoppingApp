package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        return optionalProduct.get();
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public void deleteById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        repository.deleteById(id);
    }

}
