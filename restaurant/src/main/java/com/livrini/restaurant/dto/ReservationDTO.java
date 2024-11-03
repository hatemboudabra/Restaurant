package com.livrini.restaurant.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReservationDTO {
    private Integer userId;
    private Long restaurantId;
    private Date reservationDate;
    private int numberOfGuests;
}
