package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Payment;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.CommandeRepository;
import com.livrini.restaurant.repository.RestaurantRepo;
import com.livrini.restaurant.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService implements CommandeSer {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RestaurantRepo restaurantRepository;

    @Override
    public String createOrderAndCharge(CommandeDTO commandeDTO) {
        User user = userRepository.findById(Math.toIntExact(commandeDTO.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(commandeDTO.getRestaurantId()) // Fetch the restaurant
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Commande commande = new Commande();
        commande.setDate(new Date());
        commande.setStatus("Pending");
        commande.setUser(user);
        commande.setRestaurant(restaurant);
        commandeRepository.save(commande);

        try {
            Payment payment = new Payment();
            payment.setAmount(commandeDTO.getTotalAmount());
            payment.setDate(LocalDateTime.now());
            payment.setUser(user);
            payment.setCommande(commande);

            paymentService.charge(commandeDTO.getTotalAmount(), commandeDTO.getCurrency(), commandeDTO.getDescription(), commandeDTO.getStripeToken());
            payment.setStatus("Paid");

            paymentService.savePayment(payment);

            return "Order created and payment successful!";
        } catch (Exception e) {
            commande.setStatus("Payment Failed");
            commandeRepository.save(commande);
            return "Payment failed: " + e.getMessage();
        }
    }


/*
    @Override
    public List<Commande> getallcommandes() {
        return commandeRepository.findAll();

    }

    @Override
    public Optional<Commande> getcommande(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public void deletecommande(Long id) {
                commandeRepository.deleteById(id);
    }*/
}

