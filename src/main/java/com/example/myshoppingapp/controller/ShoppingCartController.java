package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;
import com.example.myshoppingapp.controller.dto.FinishPurchaseResponse;
import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
@Slf4j
public class ShoppingCartController {

    private UserService userService;
    private CartItemService cartItemService;
    private ProductService productService;

    @GetMapping
    public String showCartPage() {
        return "shoppingcart";
    }

    @PostMapping("/finishPurchase")
    public ResponseEntity finishPurchase(@Valid @RequestBody FinishPurchaseRequest request) {
        log.info("handling finish purchase request: {}", request);
        Integer orderId = cartItemService.finishPurchase(request);
        return ResponseEntity.ok(new FinishPurchaseResponse(orderId));
    }
}
