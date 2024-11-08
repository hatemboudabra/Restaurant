package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.AvisDto;
import com.livrini.restaurant.dto.AvisMapper;
import com.livrini.restaurant.entity.Avis;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.repository.AvisRepo;
import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.RestaurantRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvisServiceImp implements AvisService{
    private final AvisRepo avisRepository;
    private final RestaurantRepo restaurantRepository;
    private final CommandeRepository commandeRepository;

    @Autowired
    public AvisServiceImp(AvisRepo avisRepository, RestaurantRepo restaurantRepository, CommandeRepository commandeRepository) {
        this.avisRepository = avisRepository;
        this.restaurantRepository = restaurantRepository;
        this.commandeRepository = commandeRepository;
    }

    @Override
    @Transactional
    public AvisDto saveAvis(AvisDto avisDto) {
        Restaurant restaurant = restaurantRepository.findById(avisDto.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + avisDto.restaurantId()));
        Commande commande = commandeRepository.findById(avisDto.commandeId())
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + avisDto.commandeId()));
        Avis avis = AvisMapper.toEntity(avisDto, restaurant, commande);
        Avis savedAvis = avisRepository.save(avis);
        return AvisMapper.toDto(savedAvis);
    }

    @Override
    @Transactional
    public AvisDto updateAvis(Long id, AvisDto avisDto) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found with id: " + id));

        avis.setRating(avisDto.rating());
        avis.setComment(avisDto.comment());
        avis.setDate(avisDto.date());

        Restaurant restaurant = restaurantRepository.findById(avisDto.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + avisDto.restaurantId()));
        Commande commande = commandeRepository.findById(avisDto.commandeId())
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + avisDto.commandeId()));

        avis.setRestaurant(restaurant);
        avis.setCommande(commande);

        Avis updatedAvis = avisRepository.save(avis);
        return AvisMapper.toDto(updatedAvis);
    }

    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }

    @Override
    public AvisDto getAvisById(Long id) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found with id: " + id));
        return AvisMapper.toDto(avis);
    }

    @Override
    public List<AvisDto> getAllAvis() {
        return avisRepository.findAll()
                .stream()
                .map(AvisMapper::toDto)
                .collect(Collectors.toList());
    }

}
