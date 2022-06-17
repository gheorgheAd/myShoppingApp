package com.example.myshoppingapp.service;

import com.example.myshoppingapp.controller.dto.FinishPurchaseRequest;
import com.example.myshoppingapp.model.CartItem;
import com.example.myshoppingapp.model.Order;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.CartItemRepository;
import com.example.myshoppingapp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ProductService productService;


    @Override
    public Integer finishPurchase(FinishPurchaseRequest request) {
        log.info("creating order entity from request: {}", request);
        Order orderEntity = new Order();
        User userEntity = userService.findByEmail(request.getEmail());
        orderEntity.setUser(userEntity);
        orderEntity = orderRepository.save(orderEntity);
        Map<Integer, Integer> productIdProductCount = getProductIdProductCountMap(request);

        for (Map.Entry<Integer, Integer> entry : productIdProductCount.entrySet()) {
            Integer k = entry.getKey();
            Integer q = entry.getValue();
            Product productEntity = productService.findById(k);
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productEntity);
            cartItem.setQuantity(q);
            cartItem.setUser(userEntity);
            cartItemRepository.save(cartItem);
        }
       return orderEntity.getId();
    }

    private Map<Integer, Integer> getProductIdProductCountMap(FinishPurchaseRequest request) {
        Map<Integer, Integer> productIdProductCount = new HashMap<>();
        request.getProductIds().forEach(it -> {
            if (productIdProductCount.containsKey(it.getId())) {
                Integer productCount = productIdProductCount.get(it.getId());
                productCount = productCount + 1;
                productIdProductCount.put(it.getId(), productCount);
            } else {
                productIdProductCount.put(it.getId(), 1);
            }
        });
        return productIdProductCount;
    }
}
