package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUser_Id(Long userId);
    @Query("UPDATE Commande c SET c.status = :status WHERE c.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") Status status);

}
