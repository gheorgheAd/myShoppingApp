package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoCartItemFound;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.OrderService;
import com.example.myshoppingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    private final CartItemService cartItemService;
    private final UserService userService;

    private final OrderService orderService;

    public CartController(CartItemService cartItemService, UserService userService, OrderService orderService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/addCart")
    public String addCart(@RequestParam Integer productId) throws NoUserFoundException {
        cartItemService.addCartItem(productId);
        return "redirect:/products";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        cartItemService.deleteCartItemById(id);
        return "redirect:/cart";
    }

    @GetMapping("/deleteCartItemsByUserId/{id}")
    public String deleteCartItemsByUser(@PathVariable Integer id) {
        cartItemService.deleteCartItemsByUser(id);
        return "redirect:/products";
    }

    @GetMapping("/updateQuantity/{id}")
    public String updateQuantity(@PathVariable Integer id) throws NoCartItemFound {
        CartItem cartItem = cartItemService.findCartItemById(id);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemService.saveCartItem(cartItem);
        return "redirect:/cart";
    }

    @GetMapping
    public String showUserCart(ModelMap modelMap) {
        Integer userId = userService.getCurrentUserId();
        List<CartItem> cartItems = cartItemService.findCartItemsByUserId(userId);
        modelMap.addAttribute("loggedUserId", userId);
        modelMap.addAttribute("cartItems", cartItems);
        modelMap.addAttribute("totalToPay", cartItemService.getCartTotal(cartItems));
        return "cart";
    }

}
