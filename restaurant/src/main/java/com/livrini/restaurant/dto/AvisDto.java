package com.livrini.restaurant.dto;

import java.util.Date;

public record AvisDto(Long id,
                      String rating,
                      String comment,
                      Date date,
                      Long restaurantId,
                      Long commandeId) {
}

