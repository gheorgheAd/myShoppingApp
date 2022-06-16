package com.example.myshoppingapp.service;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;

public interface CartItemService {
    Integer finishPurchase( FinishPurchaseRequest request);
}
