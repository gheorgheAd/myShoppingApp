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

    @GetMapping("/admin")
    public String showProductsForAdmin(ModelMap modelMap) {
        List<Product> products = service.findAll();
        modelMap.addAttribute("products", products);
        return "products-administration";
    }

    @GetMapping("/admin/add")
    public String addProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("addPageTitle", "Add Product In Shop");
        return "add-product-form";
    }

    @PostMapping("/admin/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        service.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully !");
        return "redirect:/products/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        try {
            Product product = service.findById(id);
            modelMap.addAttribute(product);
            modelMap.addAttribute("editPageTitle", "Edit product (ID " + id + ")");
            redirectAttributes.addFlashAttribute("message", "Product successfully updated !");
            return "edit-product-form";
        } catch (NoProductFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/products/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProductById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Product successfully deleted !");
        } catch (NoProductFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/products/admin";
    }
}

