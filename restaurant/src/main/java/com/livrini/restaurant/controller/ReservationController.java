package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.CommandeDTO;
import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationsByUser(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Reservation>> getReservationsByRestaurant(@PathVariable Long restaurantId) {
        List<Reservation> reservations = reservationService.getReservationsByRestaurant(restaurantId);
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/deletRc/{id}")
    public ResponseEntity<Void> annuleReservation(@PathVariable Long id) {
        reservationService.annuleReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateR/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservations = reservationService.updateReseration(id, reservationDTO);
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/allR")
    public ResponseEntity<List<Reservation>> getAllreservationss() {
        List<Reservation> reservations = reservationService.getallreservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}