package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @PostMapping("/create")
    public String createOrder(@RequestBody CommandeDTO commandeDTO) {
        return commandeService.createOrderAndCharge(commandeDTO);
    }/*
    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getallcommandes();
        return ResponseEntity.ok(commandes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getcommande(id);
        return commande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deletecommande(id);
        return ResponseEntity.noContent().build();
    }*/
}
