package com.example.myshoppingapp.controller.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
public class FinishPurchaseRequest {

    @NonNull
    public Map<Integer, Integer> productIdProductCount;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NonNull
    private Long phoneNumber;
    @NotEmpty
    private String address;
}
