package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.RestaurantDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/allRes")
    public ResponseEntity<List<Restaurant>> getAllCRestaurant() {
        List<Restaurant> restaurants = restaurantService.getAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    @GetMapping("rest/{id}")
    public ResponseEntity<Restaurant> getCommande(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getResBYId(id);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
