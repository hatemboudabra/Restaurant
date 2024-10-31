package com.livrini.restaurant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Integer userId; // ID de l'utilisateur
    private Long restaurantId; // ID du restaurant
    private LocalDateTime reservationDate; // Date et heure de la réservation
    private int numberOfGuests; // Nombre d'invités
}
