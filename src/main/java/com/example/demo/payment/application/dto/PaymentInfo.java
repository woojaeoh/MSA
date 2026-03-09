package com.example.demo.payment.application.dto;

import com.example.demo.payment.domain.model.Payment;
import com.example.demo.payment.domain.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentInfo(
        UUID id,
        String orderId,
        String paymentKey,
        Long amount,
        PaymentStatus status,
        String method,
        LocalDateTime requestedAt,
        LocalDateTime approvedAt,
        String failReason
) {

    public static PaymentInfo from(Payment payment) {
        return new PaymentInfo(
                payment.getId(),
                payment.getOrderId(),
                payment.getPaymentKey(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getRequestedAt(),
                payment.getApprovedAt(),
                payment.getFailReason()
        );
    }
}
