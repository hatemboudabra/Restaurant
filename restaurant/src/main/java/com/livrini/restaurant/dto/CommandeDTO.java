package com.livrini.restaurant.dto;

import lombok.Data;

@Data
public class CommandeDTO {
    private Double totalAmount;
    private String currency;
    private String description;
    private String stripeToken;
    private Long userId;
    private Long restaurantId;
}
