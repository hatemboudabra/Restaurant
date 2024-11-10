package com.livrini.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @OneToMany(mappedBy = "commande")
    private List<Payment> payments;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "commande")
    private List<Avis> avis;
    //@ManyToOne
   // @JoinColumn(name = "restaurant_id", nullable = false)
    //private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    private Menu menu;
    public Long getMenuId() {
        return menu != null ? menu.getId() : null;
    }
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
    public String getMenuName() {
        return menu != null ? menu.getName() : null;
    }
    public String getUserName() {
        return user != null ? user.getUsername() : null;
    }
    public float getMenuPrice() {
        return menu != null ? menu.getPrice() : null;
    }
}
