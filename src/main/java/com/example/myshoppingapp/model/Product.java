package com.example.myshoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String title;

    private String description;

    private String image;

    private Float price;

    private String producer;

}
