package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping
    public String showProducts(ModelMap modelMap) {
        List<Product> products = service.findAll();
        modelMap.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public String showProductById(@PathVariable Long id, ModelMap modelMap) {
        Product product = service.findById(id);
        modelMap.addAttribute("productById", product);
        return "product-description";
    }
}

