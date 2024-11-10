package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUser_Id(Long userId);
}
