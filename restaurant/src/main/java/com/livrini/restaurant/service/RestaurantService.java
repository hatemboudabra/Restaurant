package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.RestaurantDTO;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.repository.RestaurantRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements RestaurantSer{

    private final RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurantRepo.save(restaurant);
        return restaurantDTO;
    }

    @Override
    public Optional<Restaurant> getResBYId(Long id) {
        return restaurantRepo.findById(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }
}
