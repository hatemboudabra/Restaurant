package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.Status;
import com.livrini.restaurant.service.CommandeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
public class CommandeController {

   private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @PostMapping("/addCommande")
    public ResponseEntity<CommandeDTO> addCommande(@RequestBody CommandeDTO commandeDTO) {

        CommandeDTO savedCommande = commandeService.addCommande(commandeDTO);
        return new ResponseEntity<>(savedCommande, HttpStatus.CREATED);
    }
    @GetMapping("/livreur/{livreurId}")
    public List<Commande> getCommandesByLivreurId(@PathVariable Long livreurId) {
        return commandeService.getByLivreurId(livreurId);
    }
    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<Commande> updateStatusById(@PathVariable Long id, @RequestParam Status status) {
        try {
            Commande updatedCommande = commandeService.updateStatusById(id, status);
            return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/assignLivreur/{commandeId}")
    public ResponseEntity<Commande> assignLivreurToCommande(
            @PathVariable Long commandeId,
            @RequestParam Integer livreurId) {
        System.out.println("assignLivreurToCommande called with commandeId: " + commandeId + " and livreurId: " + livreurId);
        // logique ici
        try {
            Commande updatedCommande = commandeService.assignLivreurToCommande(commandeId, livreurId);
            return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Commande>> getCommandeByUser(@PathVariable Long userId) {
        List<Commande> commandes = commandeService.getCommandeByUser(userId);
        return ResponseEntity.ok(commandes);
    }
    @GetMapping("/allc")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getallcommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
    @GetMapping("filtre/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getcommande(id);
        return commande.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updatec/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        try {
            Commande updatedCommande = commandeService.updatecommande(id, commandeDTO);
            return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletec/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deletecommande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/top-5-users")
    public List<Object[]> getTop5UsersWithMostOrders() {
        return commandeService.getTop5UsersWithMostOrders();
    }
}
