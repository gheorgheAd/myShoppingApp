package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoCartItemFound;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.service.CartItemService;
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

    public CartController(CartItemService cartItemService, UserService userService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @GetMapping("/addCart")
    public String addCart(@RequestParam Integer productId) throws NoUserFoundException {
        cartItemService.addToCart(productId);
        return "redirect:/products";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        cartItemService.delete(id);
        return "redirect:/cart";
    }

    @GetMapping("/updateQuantity/{id}")
    public String updateQuantity(@PathVariable Integer id) throws NoCartItemFound {
        CartItem cartItem = cartItemService.findCartItemById(id);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemService.save(cartItem);
        return "redirect:/cart";
    }

    @GetMapping
    public String showUserCart(ModelMap modelMap) {
        Integer userId = userService.getCurrentUserId();
        List<CartItem> cartItems = cartItemService.findCartItemsByUserId(userId);
        modelMap.addAttribute("cartItems", cartItems);
        modelMap.addAttribute("totalToPay", cartItemService.getCartTotal(cartItems));
        return "cart";
    }

}
