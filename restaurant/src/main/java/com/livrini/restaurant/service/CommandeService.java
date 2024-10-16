package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;

import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.RestaurantRepo;
import com.livrini.restaurant.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandeService implements CommandeSer {

    private final CommandeRepository commandeRepository;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    public CommandeService(CommandeRepository commandeRepository, UserRepo userRepo, RestaurantRepo restaurantRepo) {
        this.commandeRepository = commandeRepository;
        this.userRepo = userRepo;
        this.restaurantRepo = restaurantRepo;
    }


    @Override
    public CommandeDTO addCommande(CommandeDTO commandeDTO) {
        Commande commande = new Commande();
        commande.setDate(commandeDTO.getDate());
        commande.setStatus(commandeDTO.getStatus());
        User user = userRepo.findById(Math.toIntExact(commandeDTO.getUserId())).orElseThrow(() -> new RuntimeException("User not found"));
        commande.setUser(user);
        Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(commandeDTO.getRestaurantId());
        if (!optionalRestaurant.isPresent()) {
            throw new RuntimeException("Restaurant not found");
        }
        Restaurant restaurant = optionalRestaurant.get();
        commande.setRestaurant(restaurant);
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
            Restaurant restaurant = restaurantRepo.findById(commandeDTO.getRestaurantId()).orElse(null);
            if (restaurant != null) {
                commande.setRestaurant(restaurant);
            }
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
}




