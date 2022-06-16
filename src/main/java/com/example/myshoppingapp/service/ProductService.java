package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        return optionalProduct.get();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) throws NoProductFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        productRepository.deleteById(id);
    }

}
