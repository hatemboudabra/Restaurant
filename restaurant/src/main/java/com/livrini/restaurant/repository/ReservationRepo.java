package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId); // Trouver les réservations par ID utilisateur
    List<Reservation> findByRestaurantId(Long restaurantId);
    List<Reservation> findByStatus(ReservationStatus status);// Trouver les réservations par ID restaurant
}
