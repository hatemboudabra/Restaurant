package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.RestaurantDTO;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @PostMapping("addRest")
    public RestaurantDTO addResat(RestaurantDTO restaurantDTO){
        return  restaurantService.addRestaurant(restaurantDTO);
    }

}
