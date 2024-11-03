package com.livrini.restaurant.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private String name;
    private String description;
    private String price;
    private String image;
    private Long restaurantId;




}
