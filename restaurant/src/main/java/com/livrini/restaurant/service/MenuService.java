package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.MenuDto;
import com.livrini.restaurant.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    MenuDto addMenu(MenuDto menuDto);
    Menu updayeMenu(Long id,MenuDto menuDto);
    List<Menu>findallMenu();
    Optional<Menu> findById(Long id);
    void DeleteMenu(Long id);

}
