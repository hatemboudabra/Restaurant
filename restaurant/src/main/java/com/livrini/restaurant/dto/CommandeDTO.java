package com.livrini.restaurant.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommandeDTO {
    private Date date;
    private String status;
    private Long userId;
    private Long restaurantId;
}
