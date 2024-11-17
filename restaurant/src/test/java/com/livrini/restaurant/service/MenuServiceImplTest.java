package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.MenuDto;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.repository.MenuRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuServiceImplTest {

    @InjectMocks
    private MenuServiceImpl menuService;

    @Mock
    private MenuRepo menuRepo;

    @Mock
    private RestaurantRepo restaurantRepo;

    private MenuDto menuDto;
    private Menu menu;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
   //     restaurant = new Restaurant(1L, "Test Restaurant", "Test Address", "1234567890", null, null);
        //menuDto = new MenuDto("Pizza", "Delicious pizza", "10.99", "pizza.jpg", 1L);
   //     menu = new Menu(1L, "Pizza", "Delicious pizza", "10.99", "pizza.jpg", new ArrayList<>(), new ArrayList<>(), restaurant);
    }


    @Test
    void shouldAddMenu_WhenRestaurantExists() {
        when(restaurantRepo.findById(menuDto.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(menuRepo.save(any(Menu.class))).thenReturn(menu);

        MenuDto result = menuService.addMenu(menuDto);

        assertNotNull(result, "The result should not be null");
        assertEquals(menuDto.getName(), result.getName(), "The menu name should match");
        verify(menuRepo, times(1)).save(any(Menu.class));
    }

    @Test
    void shouldThrowException_WhenRestaurantNotFound() {
        when(restaurantRepo.findById(menuDto.getRestaurantId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> menuService.addMenu(menuDto),
            "Expected addMenu to throw, but it didn't");

        assertEquals("Restaurant not found", exception.getMessage(), "Expected exception message did not match");
        verify(menuRepo, never()).save(any(Menu.class));
    }

    @Test
    void shouldUpdateMenu_WhenMenuExists() {
        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(restaurantRepo.findById(menuDto.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(menuRepo.save(any(Menu.class))).thenReturn(menu);

        Menu updatedMenu = menuService.updateMenu(menu.getId(), menuDto);

        assertEquals(menu.getId(), updatedMenu.getId(), "The updated menu ID should match");
        assertEquals(menuDto.getName(), updatedMenu.getName(), "The updated menu name should match");
        verify(menuRepo, times(1)).save(any(Menu.class));
    }

    @Test
    void shouldThrowException_WhenMenuNotFound() {
        Long nonExistentMenuId = 1L;
        when(menuRepo.findById(nonExistentMenuId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            menuService.updateMenu(nonExistentMenuId, menuDto);
        }, "Expected updateMenu to throw, but it didn't");

        assertEquals("Menu with id " + nonExistentMenuId + " not found", exception.getMessage(),
            "Expected error message does not match");
    }

    @Test
    void shouldReturnListOfMenus() {
        when(menuRepo.findAll()).thenReturn(List.of(menu));

        List<Menu> menus = menuService.findallMenu();

        assertFalse(menus.isEmpty(), "The menu list should not be empty");
        assertEquals(1, menus.size(), "Expected one menu in the list");
    }

    @Test
    void shouldReturnMenu_WhenMenuExists() {
        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));

        Optional<Menu> result = menuService.findById(menu.getId());

        assertTrue(result.isPresent(), "The menu should be present");
        assertEquals(menu.getId(), result.get().getId(), "The returned menu ID should match");
    }

    @Test
    void shouldDeleteMenu_WhenMenuExists() {
        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));
        doNothing().when(menuRepo).deleteById(menu.getId());

        menuService.DeleteMenu(menu.getId());

        verify(menuRepo, times(1)).deleteById(menu.getId());
    }

}
