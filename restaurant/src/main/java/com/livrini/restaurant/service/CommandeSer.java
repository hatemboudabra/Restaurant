package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;

public interface CommandeSer {
    public String createOrderAndCharge(CommandeDTO commandeDTO);
}
