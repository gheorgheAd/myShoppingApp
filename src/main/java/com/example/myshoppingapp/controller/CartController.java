package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.repository.CartItemRepository;
import com.example.myshoppingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;


    public CartController(CartItemRepository itemRepository, UserService userService) {
        this.cartItemRepository = itemRepository;
        this.userService = userService;
    }


    @PostMapping("/items")
    ResponseEntity<CartItem> addItem(@RequestBody CartItem itemToAdd) {
        // check if product P for userId U already exists
        // if yes -> edit the item and increase quantity
        // if no -> add the item to cart
        return ResponseEntity.ok(cartItemRepository.save(itemToAdd));
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<CartItem>> getUserItems(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartItemRepository.findCartItemByUserId(userId));
    }

    @GetMapping("/addCart")
    public String addCart(@RequestParam Integer productId, @RequestParam Integer quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Integer userId = userService.findByUsername(username).getId();
        return "redirect:/products";
    }

    @GetMapping()
    public String showCartPage() {
        return "cart";
    }
}
