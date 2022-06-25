package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.Order;
import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.OrderService;
import com.example.myshoppingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
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

    @GetMapping("/checkout")
    public String checkout(ModelMap modelMap) throws NoUserFoundException {
        orderService.checkout();
        cartItemService.deleteCartItemsByUser(userService.getCurrentUserId());
        modelMap.addAttribute("user", userService.findById(userService.getCurrentUserId()));
        return "checkout";
    }

    @GetMapping("/orders")
    public String showUserOrders(ModelMap modelMap) {
        Integer userId = userService.getCurrentUserId();
        List<Order> orders = orderService.findOrdersByUserId(userId);
        modelMap.addAttribute("loggedUserId", userId);
        modelMap.addAttribute("orders", orders);
        return "orders";
    }

}

