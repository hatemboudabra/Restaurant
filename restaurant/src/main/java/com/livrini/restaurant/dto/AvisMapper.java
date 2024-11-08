package com.livrini.restaurant.dto;

import com.livrini.restaurant.entity.Avis;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Restaurant;

public class AvisMapper {
    public static AvisDto toDto(Avis avis) {
        return new AvisDto(
                avis.getId(),
                avis.getRating(),
                avis.getComment(),
                avis.getDate(),
                avis.getRestaurant().getId(),
                avis.getCommande().getId()
        );
    }

    public static Avis toEntity(AvisDto avisDto, Restaurant restaurant, Commande commande) {
        return new Avis(
                avisDto.id(),
                avisDto.rating(),
                avisDto.comment(),
                avisDto.date(),
                restaurant,
                commande
        );
    }
}
