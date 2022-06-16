package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    @SneakyThrows
    public Product findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoProductFoundException("Product not found"));
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public void deleteById(Integer id) throws NoProductFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        repository.deleteById(id);
    }

}
