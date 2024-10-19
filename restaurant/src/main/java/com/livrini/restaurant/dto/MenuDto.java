package com.livrini.restaurant.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MenuDto {
    private String name;
    private String description;
    private String price;
    private String image;
    private Long restaurantId;




}
