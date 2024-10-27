package com.livrini.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    @Column(name = "image")
    private String image;
     @OneToMany(mappedBy = "menu")
     @JsonIgnore
     private List<Commande> commandes;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;
    public Long getRestaurantId() {
        return restaurant != null ? restaurant.getId() : null;
    }

    // Méthode pour obtenir le nom du restaurant
    public String getRestaurantName() {
        return restaurant != null ? restaurant.getName() : null;
    }
}
