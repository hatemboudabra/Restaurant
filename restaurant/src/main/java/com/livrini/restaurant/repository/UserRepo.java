package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

}
