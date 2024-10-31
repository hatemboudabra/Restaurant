package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findById(Integer id);

}
