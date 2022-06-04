package com.example.myshoppingapp.model;

import com.example.myshoppingapp.shoppingcart.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchased_item_id")
    private CartItem cartItem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
