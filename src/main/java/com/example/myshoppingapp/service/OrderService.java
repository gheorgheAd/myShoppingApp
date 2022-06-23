package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.Order;
import com.example.myshoppingapp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartItemService cartItemService;

    private final UserService userService;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void checkout() throws NoUserFoundException {
        Integer userId = userService.getCurrentUserId();
        Float totalPrice = cartItemService.getCartTotal(cartItemService.findCartItemsByUserId(userId));
        Order order = new Order(totalPrice, LocalDate.now(), userService.findById(userId));
        orderRepository.save(order);

    }

    public List<Order> findOrdersByUserId(Integer userId) {
        return orderRepository.findOrdersByUserId(userId);
    }
}

