package com.livrini.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "avis")
public class Avis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rating;
    private String comment;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
   // @JsonIgnore
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
   // @JsonIgnore
    private Commande commande;
}
