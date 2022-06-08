package com.example.myshoppingapp.service.impl;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        return optionalProduct.get();
    }

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        repository.deleteById(id);
    }
}
