package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoCartItemFound;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    private final UserService userService;

    private final ProductService productService;

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> findCartItemsByUserId(Integer userId) {
        return cartItemRepository.findCartItemsByUserId(userId);
    }

    public boolean existsByUserIdAndProductId(Integer userId, Integer productId) {
        return cartItemRepository.existsByUserIdAndProductId(userId, productId);
    }

    public void addToCart(Integer productId) throws NoUserFoundException {
        Integer userId = userService.getCurrentUserId();
        CartItem cartItem;

        if (cartItemRepository.existsByUserIdAndProductId(userId, productId)) {
            cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        } else {
            cartItem = new CartItem(1, userService.findById(userId), productService.findById(productId));
            cartItemRepository.save(cartItem);
        }
    }

    public Float getCartTotal(List<CartItem> cartItems) {
        Float cartTotal = 0.0F;

        for (CartItem cartItem : cartItems) {
            cartTotal+= cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return cartTotal;
    }

    public void delete(Integer id) {
        cartItemRepository.deleteById(id);
    }

    public CartItem findCartItemById(Integer id) throws NoCartItemFound {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if(optionalCartItem.isEmpty()) {
            throw new NoCartItemFound("Cart item not found");
        }
        return optionalCartItem.get();
    }
}
