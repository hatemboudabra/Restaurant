package com.livrini.restaurant.service;

import com.livrini.restaurant.entity.Role;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.RoleRepo;
import com.livrini.restaurant.repository.UserRepo;
import com.livrini.restaurant.service.exception.EmailAlreadyExistsException;
import com.livrini.restaurant.service.registre.RegistationRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public User registerUser(RegistationRequest request) {
        Optional<User> optionalUser = userRepo.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("email deja utilisé");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setEnabled(false);
        userRepo.save(user);
        Role r =roleRepo.findByRole("USER");
        List<Role> roles =new ArrayList<>();
        roles.add(r);
        user.setRoles(roles);


        return userRepo.save(user);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByRole(rolename);
        user.getRoles().add(role);
        return user;
    }

}
