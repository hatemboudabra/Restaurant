package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.Status;

import java.util.List;
import java.util.Optional;

public interface CommandeSer {
    public CommandeDTO addCommande(CommandeDTO commandeDTO);
    public List<Commande> getallcommandes();
    public Optional<Commande> getcommande(Long id);
    List<Commande> getCommandeByUser(Long userId);
   public Commande updatecommande(Long id , CommandeDTO commandeDTO);
    public List<Commande> getByLivreurId(Long livreurId) ;

    Commande assignLivreurToCommande(Long commandeId, Integer livreurId);

    Commande updateStatusById(Long id, Status status);

    public void deletecommande(Long id);
    public List<Object[]> getTop5UsersWithMostOrders();

}
