package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.service.CartItemService;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class ShoppingCartController {

    private UserService userService;
    private CartItemService cartItemService;
    private ProductService productService;

    @GetMapping
    public String showCartPage() {
        return "shoppingcart";
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
