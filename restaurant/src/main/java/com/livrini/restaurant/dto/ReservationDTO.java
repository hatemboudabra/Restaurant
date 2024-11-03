package com.livrini.restaurant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Integer userId;
    private Long restaurantId;
    private LocalDateTime reservationDate;
    private int numberOfGuests;
}
