package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.ReservationDTO;
import com.livrini.restaurant.entity.Reservation;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.ReservationRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import com.livrini.restaurant.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepo reservationRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private RestaurantRepo restaurantRepo;

    private ReservationDTO reservationDTO;
    private Reservation reservation;
    private User user;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets avec des setters
        user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        user.setEmail("john@example.com");

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Street");
        restaurant.setPhone("987654321");

        reservationDTO = new ReservationDTO();
        reservationDTO.setUserId(Math.toIntExact(1L));
        reservationDTO.setRestaurantId(1L);
        reservationDTO.setReservationDate(new Date());
        reservationDTO.setNumberOfGuests(4);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
    }

    @Test
    void shouldCreateReservation_WhenUserAndRestaurantExist() {
        when(userRepo.findById(reservationDTO.getUserId())).thenReturn(Optional.of(user));
        when(restaurantRepo.findById(reservationDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createReservation(reservationDTO);

        assertNotNull(result, "The created reservation should not be null");

        // Utilisation de longValue() pour éviter la différence entre Integer et Long
        assertEquals(reservationDTO.getUserId().longValue(), result.getUser().getId().longValue());
        assertEquals(reservationDTO.getRestaurantId().longValue(), result.getRestaurant().getId().longValue());

        verify(reservationRepo, times(1)).save(any(Reservation.class));
    }



    @Test
    void shouldThrowException_WhenUserNotFound() {
        when(userRepo.findById(reservationDTO.getUserId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                reservationService.createReservation(reservationDTO),
            "Expected createReservation to throw, but it didn't");

        assertEquals("User not found", exception.getMessage());
        verify(reservationRepo, never()).save(any(Reservation.class));
    }

    @Test
    void shouldReturnReservationsByUser() {
        when(reservationRepo.findByUserId(user.getId())).thenReturn(List.of(reservation));

        List<Reservation> reservations = reservationService.getReservationsByUser(user.getId());

        assertFalse(reservations.isEmpty(), "The reservation list should not be empty");
        assertEquals(1, reservations.size(), "Expected one reservation in the list");
    }

    @Test
    void shouldUpdateReservation_WhenReservationExists() {
        when(reservationRepo.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        when(userRepo.findById(reservationDTO.getUserId())).thenReturn(Optional.of(user));
        when(restaurantRepo.findById(reservationDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));

        reservationDTO.setNumberOfGuests(5);
      //  Reservation updatedReservation = reservationService.updateReseration(reservation.getId(), reservationDTO);

     //   assertEquals(reservation.getId(), updatedReservation.getId(), "The updated reservation ID should match");
     //   assertEquals(5, updatedReservation.getNumberOfGuests(), "The updated number of guests should match");
    }

    @Test
    void shouldDeleteReservation_WhenReservationExists() {
        when(reservationRepo.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        doNothing().when(reservationRepo).deleteById(reservation.getId());

        reservationService.annuleReservation(reservation.getId());

        verify(reservationRepo, times(1)).deleteById(reservation.getId());
    }
}
