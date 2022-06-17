package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoProductFoundException("Product not found"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) throws NoProductFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoProductFoundException("Product not found!");
        }
        productRepository.deleteById(id);
    }

}
