package com.livrini.restaurant.repository;

import com.livrini.restaurant.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
