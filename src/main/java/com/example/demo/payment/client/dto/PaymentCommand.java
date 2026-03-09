package com.example.demo.payment.client.dto;

public record PaymentCommand(
        String paymentKey,
        String orderId,
        Long amount
) {
}
