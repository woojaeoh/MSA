package com.example.demo.payment.domain.model.repository;

import com.example.demo.payment.domain.model.Payment;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Page<Payment> findAll(Pageable pageable);

    Optional<Payment> findById(UUID id);

    Payment save(Payment payment);
}
