package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.MenuDto;
import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservationDTO);
    List<Reservation> getReservationsByUser(Long userId);
    List<Reservation> getReservationsByRestaurant(Long restaurantId);
    Reservation updateReseration(Long id, ReservationDTO reservationDTO);
    void annuleReservation (Long id);
}
