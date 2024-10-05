package com.livrini.restaurant.controller;

import com.livrini.restaurant.entity.Role;
import com.livrini.restaurant.entity.User;
import com.livrini.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    UserService userService;
    @PostMapping("/addRole/{username}/{rolename}")
    public ResponseEntity<User> addRoleToUser(@PathVariable String username, @PathVariable String rolename) {

        User updatedUser = userService.addRoleToUser(username, rolename);

        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role savedRole = userService.addRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
}
