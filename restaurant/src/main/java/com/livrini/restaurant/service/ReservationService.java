package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservationDTO); // Créer une réservation
    List<Reservation> getReservationsByUser(Long userId); // Obtenir les réservations par utilisateur
    List<Reservation> getReservationsByRestaurant(Long restaurantId); // Obtenir les réservations par restaurant
}
