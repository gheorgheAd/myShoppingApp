package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    private final CartItemRepository cartItemRepository;

    public CartController(CartItemRepository itemRepository) {
        this.cartItemRepository = itemRepository;
    }

    @PostMapping("/items")
    ResponseEntity<CartItem> addItem(@RequestBody CartItem itemToAdd) {
        // check if product P for userId U already exists
        // if yes -> edit the item and increase quantity
        // if no -> add the item to cart
        return ResponseEntity.ok(cartItemRepository.save(itemToAdd));
    }
    @GetMapping("/products-administration/add")
    public String addToCart(ModelMap modelMap) {
        modelMap.addAttribute("cartItem", new CartItem());
        modelMap.addAttribute("pageTitleMessage", "Add Product In Shop");
        return "/admin-files/add-product-form";
    }
    @GetMapping("/{userId}")
    ResponseEntity<List<CartItem>> getUserItems(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartItemRepository.findCartItemByUserId(userId));
    }
}
