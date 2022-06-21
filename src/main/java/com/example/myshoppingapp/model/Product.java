package com.example.myshoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)

    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String producer;

}
