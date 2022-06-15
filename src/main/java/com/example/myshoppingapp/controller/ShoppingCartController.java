package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.service.CartItemServiceImpl;
import com.example.myshoppingapp.service.ProductServiceImpl;
import com.example.myshoppingapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    UserServiceImpl userServiceImpl;
    CartItemServiceImpl cartItemServiceImpl;
    ProductServiceImpl productServiceImpl;

    @Autowired
    public ShoppingCartController(
            UserServiceImpl userServiceImpl,
            CartItemServiceImpl cartItemServiceImpl,
            ProductServiceImpl productServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartItemServiceImpl = cartItemServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", cartItemServiceImpl.getProductsInCart());
        modelAndView.addObject("total", cartItemServiceImpl.getTotal().toString());
        return modelAndView;
    }


    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId){
        productServiceImpl.findById(productId).ifPresent(cartItemServiceImpl::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId){
        productServiceImpl.findById(productId).ifPresent(cartItemServiceImpl::removeProduct);
        return shoppingCart();
    }

//    @GetMapping("/shoppingCart/checkout")
//    public ModelAndView checkout() {
//        try {
//            cartItemServiceImpl.checkout();
//        } catch (NotEnoughProductsInStockException e) {
//            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
//        }
//        return shoppingCart();
//    }
}
