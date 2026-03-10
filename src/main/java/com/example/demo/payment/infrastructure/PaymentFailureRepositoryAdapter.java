package com.example.demo.payment.infrastructure;

import com.example.demo.payment.domain.model.PaymentFailure;
import com.example.demo.payment.domain.model.repository.PaymentFailureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentFailureRepositoryAdapter implements PaymentFailureRepository {

    private final PaymentFailureJpaRepository paymentFailureJpaRepository;

    @Override
    public PaymentFailure save(PaymentFailure failure) {
        return paymentFailureJpaRepository.save(failure);
    }
}
