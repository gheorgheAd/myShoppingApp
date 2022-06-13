package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShoppingCartController {

    UserService userService;
    CartItemService cartItemService;
    ProductService productService;
    Long userId = 1L;

    @Autowired
    public ShoppingCartController(
            UserService userService,
            CartItemService cartItemService,
            ProductService productService){
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(ModelMap modelMap) {
        List<CartItem> productsForCurrentUser = cartItemService.findCartItemByUserId(userId);

        modelMap.addAttribute("products");
        return "shoppingCart";
    }

//    @GetMapping("/shoppingCart/addProduct/{productId}")
//    public String addProductToCart(@PathVariable Long productId) throws NoProductFoundException {
//        cartItemService.addProduct(productService.findById(productId));
//        return shoppingCart();
//    }
//
//    @GetMapping("/shoppingCart/removeProduct/{productId}")
//    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
//        return shoppingCart();
//    }
}
