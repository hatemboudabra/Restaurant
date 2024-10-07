package com.livrini.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String status;

    @OneToMany(mappedBy = "commande")
    private List<Payment> payments;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
