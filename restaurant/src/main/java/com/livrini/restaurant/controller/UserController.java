package com.livrini.restaurant.controller;

import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.repository.UserRepo;
import com.livrini.restaurant.service.UserService;
import com.livrini.restaurant.service.registre.RegistationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Test")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    @Operation(description = "user")
    @PostMapping("register")
    public User register(@RequestBody RegistationRequest request){
        return userService.registerUser(request);

    }
    @GetMapping("/{username}")
    public Map<String, Object> getUserIdByUsername(@PathVariable String username) {
        User user = userRepo.findByUsername(username);
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("id", user.getId());
        } else {
            response.put("error", "User not found");
        }
        return response;
    }
}
