package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CartItemController {

    @PostMapping("/finishPurchase")
    public ResponseEntity finishPurchase(@Valid @RequestBody FinishPurchaseRequest request) {
        return ResponseEntity.ok().build();
    }
}
