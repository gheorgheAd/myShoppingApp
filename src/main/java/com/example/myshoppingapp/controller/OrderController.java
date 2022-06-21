package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.OrderService;
import com.example.myshoppingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final CartItemService cartItemService;

    private final UserService userService;

    public OrderController(OrderService orderService, CartItemService cartItemService, UserService userService) {
        this.orderService = orderService;
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @GetMapping()
    public String checkout() throws NoUserFoundException {
        orderService.checkout();
        cartItemService.deleteCartItemsByUser(userService.getCurrentUserId());
        return "checkout";
    }
}

