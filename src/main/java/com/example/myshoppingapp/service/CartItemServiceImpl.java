package com.example.myshoppingapp.service;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;
import com.example.myshoppingapp.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService{

    private final ProductService service;

    @Override
    public Integer finishPurchase(FinishPurchaseRequest request) {
        request.getProductIdProductCount().forEach((k,v)->{
            Product product = service.findById(k);
        });
        return null;
    }
}
