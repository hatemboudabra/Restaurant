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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReservation(@PathVariable Long id) {
        reservationService.annuleReservation(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Reservation with ID " + id + " has been deleted successfully.");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/updateR/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservations = reservationService.updateReservation(id, reservationDTO);
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable String status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateReservationStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        try {
            Reservation updatedReservation = reservationService.updateReservationStatus(id, status);
            return ResponseEntity.ok(updatedReservation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
