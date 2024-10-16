package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;

import java.util.List;
import java.util.Optional;

public interface CommandeSer {
    public CommandeDTO addCommande(CommandeDTO commandeDTO);
    public List<Commande> getallcommandes();
    public Optional<Commande> getcommande(Long id);
   public Commande updatecommande(Long id , CommandeDTO commandeDTO);
    public void deletecommande(Long id);

}
