package com.yuno.payment_orchestration.repository;

import com.yuno.payment_orchestration.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}