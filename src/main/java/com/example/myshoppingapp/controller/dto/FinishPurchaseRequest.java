package com.example.myshoppingapp.controller.dto;

import com.example.myshoppingapp.model.Product;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(exclude = "password")
public class FinishPurchaseRequest {
    @NotNull
    private List<Product> productIds;
    @NotEmpty
    private String userName;
    private String userSurname;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;
    private String date;
    private String password;
}
