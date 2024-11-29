package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepo extends JpaRepository<Avis, Long> {
    //List<Avis> findByMenuId(Long menuId);
}
