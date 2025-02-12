package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.AvisDto;
import com.livrini.restaurant.entity.Avis;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.repository.AvisRepo;
import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.MenuRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvisServiceImp implements AvisService{
    private final AvisRepo avisRepo;
    private final MenuRepo menuRepo;
    private final CommandeRepository commandeRepository;

    public AvisServiceImp(AvisRepo avisRepo, MenuRepo menuRepo, CommandeRepository commandeRepository) {
        this.avisRepo = avisRepo;
        this.menuRepo = menuRepo;
        this.commandeRepository = commandeRepository;

    }
    private String rating;
    private String comment;
    private Date date;
    private Long commandId;
    private Long menuId;
    private Long restaurantId;
    @Override
    public Avis addAvis(AvisDto avisDto) {
        Avis avis = new Avis();
        avis.setRating(avisDto.getRating());
        avis.setDate(avisDto.getDate());
        avis.setComment(avisDto.getComment());

        Commande commande = commandeRepository.findById(avisDto.getCommandId())
                .orElseThrow(() -> new EntityNotFoundException("Commande with ID " + avisDto.getCommandId() + " not found"));
        avis.setCommande(commande);

        Menu menu = menuRepo.findById(avisDto.getMenuId())
                .orElseThrow(() -> new EntityNotFoundException("Menu with ID " + avisDto.getMenuId() + " not found"));
        avis.setMenu(menu);


        return avisRepo.save(avis);
    }


    @Override
    public List<Avis> getallAvis() {
        return avisRepo.findAll();
    }

    @Override
    public Optional<Avis> getAvis(Long id) {
        return avisRepo.findById(id);
    }

    @Override
    public Avis updateAvis(Long id, AvisDto avisDto) {
        Avis avis = avisRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis with ID " + id + " not found"));

        avis.setRating(avisDto.getRating());
        avis.setDate(avisDto.getDate());
        avis.setComment(avisDto.getComment());

        if (avisDto.getCommandId() != null) {
            Commande commande = commandeRepository.findById(avisDto.getCommandId())
                    .orElseThrow(() -> new EntityNotFoundException("Commande with ID " + avisDto.getCommandId() + " not found"));
            avis.setCommande(commande);
        }

        if (avisDto.getMenuId() != null) {
            Menu menu = menuRepo.findById(avisDto.getMenuId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu with ID " + avisDto.getMenuId() + " not found"));
            avis.setMenu(menu);
        }



        return avisRepo.save(avis);
    }


    @Override
    public void deleteAvis(Long id) {
        avisRepo.deleteById(id);
    }


    @Override
    public Double calculateAverageRatingByMenu(Long menuId) {
        List<Avis> avisList = avisRepo.findAll();
        double sum = 0;
        int count = 0;

        for (Avis avis : avisList) {
            if (avis.getMenu().getId().equals(menuId)) {
                sum += avis.getRating();
                count++;
            }
        }

        return count > 0 ? sum / count : null;
    }

}
