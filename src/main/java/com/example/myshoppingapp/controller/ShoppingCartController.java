package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;
import com.example.myshoppingapp.controller.dto.FinishPurchaseResponse;
import com.example.myshoppingapp.service.CartItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@Slf4j
public class ShoppingCartController {

    CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    public ShoppingCartController(CartItemServiceImpl cartItemServiceImpl) {
        this.cartItemServiceImpl = cartItemServiceImpl;
    }

    @PostMapping("/finishPurchase")
    public ResponseEntity finishPurchase(@Valid @RequestBody FinishPurchaseRequest request) {
        log.info("handling finish purchase request: {}", request);
        Integer orderId = cartItemServiceImpl.finishPurchase(request);
        return ResponseEntity.ok(new FinishPurchaseResponse(orderId));
    }
}
