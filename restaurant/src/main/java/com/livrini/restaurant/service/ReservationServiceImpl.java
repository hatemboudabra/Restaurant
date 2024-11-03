package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.ReservationRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import com.livrini.restaurant.repository.UserRepo;
import com.livrini.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        User user = userRepo.findById(reservationDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepo.findById(reservationDTO.getRestaurantId()).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());

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
}
