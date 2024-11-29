package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.*;

import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.MenuRepo;
import com.livrini.restaurant.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandeService implements CommandeSer {

    private final CommandeRepository commandeRepository;
    private final UserRepo userRepo;
    private final MenuRepo menuRepo;

    public CommandeService(CommandeRepository commandeRepository, UserRepo userRepo, MenuRepo menuRepo) {
        this.commandeRepository = commandeRepository;
        this.userRepo = userRepo;
        this.menuRepo = menuRepo;
    }


    @Override
    public CommandeDTO addCommande(CommandeDTO commandeDTO) {
        Commande commande = new Commande();
        commande.setDate(commandeDTO.getDate());
        commande.setStatus(commandeDTO.getStatus());
        User user = userRepo.findById(Math.toIntExact(commandeDTO.getUserId())).get();
        commande.setUser(user);
        Optional<Menu> optionalMenu = menuRepo.findById(commandeDTO.getMenuId());
        if (!optionalMenu.isPresent()) {
            throw new RuntimeException("Menu not found");
        }
        Menu menu = optionalMenu.get();
        commande.setMenu(menu);
        commandeRepository.save(commande);
        return commandeDTO;
    }

    @Override
    public List<Commande> getallcommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Optional<Commande> getcommande(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> getCommandeByUser(Long userId) {
        return commandeRepository.findByUser_Id(userId);
    }
    public List<Commande> getByLivreurId(Long livreurId) {
        return commandeRepository.findByLivreurId(livreurId);
    }
    @Override
    public Commande updatecommande(Long id, CommandeDTO commandeDTO) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setDate(commandeDTO.getDate());
            commande.setStatus(commandeDTO.getStatus());
            User user = userRepo.findById(Math.toIntExact(commandeDTO.getUserId())).orElse(null);
            if (user != null) {
                commande.setUser(user);
            }
            Menu menu = menuRepo.findById(commandeDTO.getMenuId()).orElse(null);
            if (menu != null) {
                commande.setMenu(menu);
            }
            commandeRepository.save(commande);
            return commande;
        } else {
            throw new EntityNotFoundException("Commande with id " + id + " not found");
        }
    }
    @Override
    public Commande assignLivreurToCommande(Long commandeId, Integer livreurId) {
        // Récupérer la commande
        Optional<Commande> optionalCommande = commandeRepository.findById(commandeId);
        if (!optionalCommande.isPresent()) {
            throw new EntityNotFoundException("Commande with id " + commandeId + " not found");
        }
        Commande commande = optionalCommande.get();

        // Récupérer le livreur
        Optional<User> optionalLivreur = userRepo.findById(livreurId);
        if (!optionalLivreur.isPresent()) {
            throw new EntityNotFoundException("User (livreur) with id " + livreurId + " not found");
        }
        User livreur = optionalLivreur.get();

        // Vérifier si le livreur a le rôle de "LIVREUR"
        boolean isLivreur = livreur.getRoles().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("LIVREUR"));
        if (!isLivreur) {
            throw new RuntimeException("User with id " + livreurId + " is not a livreur");
        }

        // Affecter le livreur à la commande
        commande.setLivreur(livreur);

        // Enregistrer la commande mise à jour
        commandeRepository.save(commande);

        return commande;
    }

    @Override
    public Commande updateStatusById(Long id, Status status) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setStatus(status);
            commandeRepository.save(commande);
            return commande;
        } else {
            throw new EntityNotFoundException("Commande with id " + id + " not found");
        }
    }



    @Override
    public void deletecommande(Long id) {
                commandeRepository.deleteById(id);
    }


    @Override
    public List<Object[]> getTop5UsersWithMostOrders() {
        return commandeRepository.findTop5UsersWithMostOrders();
    }
}
