package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.repository.CartItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartItemController {

    private final CartItemRepository cartItemRepository;

    public CartItemController(CartItemRepository itemRepository) {
        this.cartItemRepository = itemRepository;
    }

    @PostMapping("/items")
    ResponseEntity<CartItem> addItem(@RequestBody CartItem itemToAdd) {
        // check if product P for userId U already exists
        // if yes -> edit the item and increase quantity
        // if no -> add the item to cart
        return ResponseEntity.ok(cartItemRepository.save(itemToAdd));
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<CartItem>> getUserItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartItemRepository.findCartItemByUserId(userId));
    }

}
