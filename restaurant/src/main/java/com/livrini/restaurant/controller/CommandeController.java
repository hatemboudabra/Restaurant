package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @PostMapping("/create")
    public String createOrder(@RequestBody CommandeDTO commandeDTO) {
        return commandeService.createOrderAndCharge(commandeDTO);
    }
}
