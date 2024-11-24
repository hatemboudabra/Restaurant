package com.livrini.restaurant.service;

import com.livrini.restaurant.entity.Role;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.service.registre.RegistationRequest;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> findAllUsers();
    User findUserByUsername (String username);
    Role addRole(Role role);
    User registerUser(RegistationRequest request);

    List<User> getAllLivreurs();

    User addRoleToUser(String username, String rolename);
}
