package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController {

    UserService userService;
    CartItemService cartItemService;
    ProductService productService;

    @Autowired
    public ShoppingCartController(
            UserService userService,
            CartItemService cartItemService,
            ProductService productService){
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

//    @GetMapping("/shoppingCart")
//    public String shoppingCart(ModelMap modelMap) {
//        List<CartItem> productsForCurrentUser = cartItemService.findCartItemByUserId(userId);
//
//        modelMap.addAttribute("products");
//        return "shoppingcart";
//    }
//
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
