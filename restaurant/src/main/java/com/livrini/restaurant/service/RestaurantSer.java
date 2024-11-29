package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.RestaurantDTO;
import com.livrini.restaurant.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantSer {

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO);
    public Optional<Restaurant> getResBYId(Long id);
    public List<Restaurant> getAll();
}
