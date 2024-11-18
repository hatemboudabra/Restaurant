/*
package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.RestaurantDTO;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.repository.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    private RestaurantRepo restaurantRepo;
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantRepo = Mockito.mock(RestaurantRepo.class);
        restaurantService = new RestaurantService(restaurantRepo);
    }

*/
/*    @Test
    void testAddRestaurant() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName("Test Restaurant");
        restaurantDTO.setAddress("123 Test St");
        restaurantDTO.setPhone("123-456-7890");

        // Simuler la sauvegarde du restaurant
        when(restaurantRepo.save(any(Restaurant.class))).thenReturn(new Restaurant(1L, "Test Restaurant", "123 Test St", "123-456-7890", new ArrayList<>(), new ArrayList<>()));

        RestaurantDTO result = restaurantService.addRestaurant(restaurantDTO);

        assertNotNull(result);
        assertEquals("Test Restaurant", result.getName());
        assertEquals("123 Test St", result.getAddress());
        assertEquals("123-456-7890", result.getPhone());
    }*//*

*/
/*

    @Test
    void testGetResBYId() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", "123 Test St", "123-456-7890", new ArrayList<>(), new ArrayList<>());
        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = restaurantService.getResBYId(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Restaurant", result.get().getName());
    }

    @Test
    void testGetAll() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1L, "Test Restaurant 1", "123 Test St", "123-456-7890", new ArrayList<>(), new ArrayList<>()));
        restaurantList.add(new Restaurant(2L, "Test Restaurant 2", "456 Test Ave", "987-654-3210", new ArrayList<>(), new ArrayList<>()));

        when(restaurantRepo.findAll()).thenReturn(restaurantList);

        List<Restaurant> result = restaurantService.getAll();

        assertEquals(2, result.size());
        assertEquals("Test Restaurant 1", result.get(0).getName());
        assertEquals("Test Restaurant 2", result.get(1).getName());
    }
*//*


}
*/
