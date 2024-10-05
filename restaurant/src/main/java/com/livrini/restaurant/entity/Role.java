package com.livrini.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long role_id;
    @Column(unique=true)
    private String role;
}
