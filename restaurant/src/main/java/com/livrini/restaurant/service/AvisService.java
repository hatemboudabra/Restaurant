package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.AvisDto;
import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Avis;
import com.livrini.restaurant.entity.Commande;

import java.util.List;
import java.util.Optional;

public interface AvisService {
    public Avis addAvis(AvisDto avisDto);
    public List<Avis> getallAvis();
    public Optional<Avis> getAvis(Long id);
    public Avis updateAvis(Long id , AvisDto avisDto);
    public void deleteAvis(Long id);
}
