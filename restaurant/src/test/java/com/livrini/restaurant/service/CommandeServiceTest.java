package com.livrini.restaurant.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.livrini.restaurant.exception.ResourceNotFoundException;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.entity.Status;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.MenuRepo;
import com.livrini.restaurant.repository.UserRepo;
import com.livrini.restaurant.service.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private UserRepo userRepo;

    @Mock
    private MenuRepo menuRepo;

    @InjectMocks
    private CommandeService commandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddCommande_WhenUserAndMenuExist() {
        // Initialisation des objets User et Menu simulés
        User mockUser = new User();
        mockUser.setId(1L);
        Menu mockMenu = new Menu();
        mockMenu.setId(1L);

        // Configurer les retours de méthode pour les mocks
        when(userRepo.findById(1)).thenReturn(Optional.of(mockUser));
        when(menuRepo.findById(1L)).thenReturn(Optional.of(mockMenu));

        // Création de CommandeDTO avec les valeurs nécessaires
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setUserId(1L);
        commandeDTO.setMenuId(1L);
        commandeDTO.setDate(new Date());
        commandeDTO.setStatus(Status.PENDING);

        // Simulation de l'enregistrement de la commande
        Commande savedCommande = new Commande();
        savedCommande.setId(1L);
        when(commandeRepository.save(any(Commande.class))).thenReturn(savedCommande);

        // Appel de la méthode et assertions
        CommandeDTO result = commandeService.addCommande(commandeDTO);

        assertNotNull(result);
        assertEquals(commandeDTO.getUserId(), result.getUserId());
        assertEquals(commandeDTO.getMenuId(), result.getMenuId());
        assertEquals(commandeDTO.getStatus(), result.getStatus());
    }




}
