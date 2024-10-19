package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.MenuDto;
import com.livrini.restaurant.entity.Commande;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.entity.Restaurant;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.MenuRepo;
import com.livrini.restaurant.repository.RestaurantRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MenuServiceImpl implements MenuService{

    private  final MenuRepo menuRepo;
    private final RestaurantRepo restaurantRepo;
    public MenuServiceImpl(MenuRepo menuRepo, RestaurantRepo restaurantRepo) {
        this.menuRepo = menuRepo;
        this.restaurantRepo = restaurantRepo;
    }
    @Override
    public MenuDto addMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());
        menu.setPrice(menuDto.getPrice());
        menu.setImage(menuDto.getImage());
        Optional<Restaurant> restaurant = restaurantRepo.findById(menuDto.getRestaurantId());
        if (restaurant.isEmpty()){
            throw new RuntimeException("Restaurant not found");
        }
        Restaurant restaurant1 = restaurant.get();
        menu.setRestaurant(restaurant1);
        menuRepo.save(menu);
        return menuDto;
    }

    @Override
    public Menu updayeMenu(Long id, MenuDto menuDto) {
            Optional<Menu> optionalMenu= menuRepo.findById(id);
            if (optionalMenu.isPresent()) {
                Menu menu = optionalMenu.get();
                menu.setName(menu.getName());
                menu.setDescription(menuDto.getDescription());
                menu.setPrice(menuDto.getPrice());
                menu.setImage(menuDto.getImage());
                Restaurant restaurant = restaurantRepo.findById(menuDto.getRestaurantId()).orElse(null);
                if (restaurant != null) {
                    menu.setRestaurant(restaurant);
                }
                menuRepo.save(menu);
                return menu;
            }
            else {
                    throw new EntityNotFoundException ("Menu with id \" + id + \" not found\"");
                }


    }

    @Override
    public List<Menu> findallMenu() {
        return menuRepo.findAll();
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return menuRepo.findById(id);
    }

    @Override
    public void DeleteMenu(Long id) {
            menuRepo.deleteById(id);
    }
}
