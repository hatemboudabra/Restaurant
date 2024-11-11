package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.ReservationStatus;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.ReservationRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import com.livrini.restaurant.repository.UserRepo;
import com.livrini.restaurant.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    @Transactional
    public Reservation createReservation(ReservationDTO reservationDTO) {
        User user = userRepo.findById(reservationDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepo.findById(reservationDTO.getRestaurantId()).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepo.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepo.findByUserId(userId);
    }

    @Override
    public List<Reservation> getReservationsByRestaurant(Long restaurantId) {
        return reservationRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public Reservation updateReservation(Long id, ReservationDTO reservationDTO) {
        // Retrieve the reservation by its ID
        Optional<Reservation> optionalReservation = reservationRepo.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Update the reservation date and number of guests from the DTO
            reservation.setReservationDate(reservationDTO.getReservationDate());
            reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());

            // Optionally update the status, if you are passing it in the DTO
            if (reservationDTO.getStatus() != null) {
                reservation.setStatus(ReservationStatus.valueOf(reservationDTO.getStatus().toUpperCase()));
            }

            // Set the user entity from the provided userId
            User user = userRepo.findById(reservationDTO.getUserId()).orElse(null);
            if (user != null) {
                reservation.setUser(user);
            } else {
                throw new EntityNotFoundException("User with id " + reservationDTO.getUserId() + " not found");
            }

            // Set the restaurant entity from the provided restaurantId
            Restaurant restaurant = restaurantRepo.findById(reservationDTO.getRestaurantId()).orElse(null);
            if (restaurant != null) {
                reservation.setRestaurant(restaurant);
            } else {
                throw new EntityNotFoundException("Restaurant with id " + reservationDTO.getRestaurantId() + " not found");
            }

            // Save the updated reservation back to the repository
            return reservationRepo.save(reservation);

        } else {
            // Throw exception if the reservation is not found
            throw new EntityNotFoundException("Reservation with id " + id + " not found");
        }
    }

    @Override
    public void annuleReservation(Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        reservation.ifPresent(res -> {
            res.setStatus(ReservationStatus.CANCELED);
            reservationRepo.save(res);
        });
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }

    @Override
    public List<Reservation> getallreservations() {
        return reservationRepo.findAll();
    }
    @Override
    public List<Reservation> getReservationsByStatus(String status) {
        ReservationStatus reservationStatus = ReservationStatus.valueOf(status.toUpperCase());  // Convertit le statut en Enum
        return reservationRepo.findByStatus(reservationStatus);
    }
}
