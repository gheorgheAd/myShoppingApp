package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private ProductService productService;
    private UserService userService;

    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String adminInterface() {
        return "/admin-files/admin-interface";
    }

    @GetMapping("/products-administration")
    public String showProductsForAdmin(ModelMap modelMap) {
        List<Product> products = productService.findAll();
        modelMap.addAttribute("products", products);
        return "/admin-files/products-administration";
    }

    @GetMapping("/products-administration/add")
    public String addProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("pageTitleMessage", "Add Product In Shop");
        return "/admin-files/add-product-form";
    }

    @PostMapping("/products-administration/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully !");
        return "redirect:/admin/products-administration";
    }

    @GetMapping("/products-administration/edit/{id}")
    public String editProduct(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findById(id);
            modelMap.addAttribute(product);
            modelMap.addAttribute("pageTitleMessage", "Edit product (ID " + id + ")");
            redirectAttributes.addFlashAttribute("message", "Product successfully updated !");
            return "admin-files/edit-product-form";
        } catch (NoProductFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/admin/products-administration";
    }

    @PostMapping("/products-administration/update")
    public String updateProduct(Product product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully !");
        return "redirect:/admin/products-administration";
    }

    @GetMapping("/products-administration/delete/{id}")
    public String deleteProductById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Product successfully deleted !");
        } catch (NoProductFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/admin/products-administration";
    }

    @GetMapping("/users-administration")
    public String showUsers(ModelMap modelMap) {
        List<User> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return "/admin-files/users-administration";
    }

    @GetMapping("/users-administration/add")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("addPageTitle", "Add User In Shop");
        return "admin-files/add-user-form";
    }

    @PostMapping("/users-administration/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User saved successfully !");
        return "redirect:/admin/users-administration";
    }

    @GetMapping("/users-administration/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            modelMap.addAttribute(user);
            modelMap.addAttribute("editPageTitle", "Edit user (ID " + id + ")");
            redirectAttributes.addFlashAttribute("message", "User successfully updated !");
            return "admin-files/edit-user-form";
        } catch (NoUserFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/admin/users-administration";
    }

    @PostMapping("/users-administration/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully !");
        return "redirect:/admin/users-administration";
    }

    @GetMapping("/users-administration/delete/{id}")
    public String deleteUserById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "User successfully deleted !");
        } catch (NoUserFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/admin/users-administration";
    }
}
