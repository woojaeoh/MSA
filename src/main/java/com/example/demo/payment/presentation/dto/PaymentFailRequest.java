package com.example.demo.payment.presentation.dto;

import com.example.demo.payment.application.dto.PaymentFailCommand;

public record PaymentFailRequest(
        String orderId,
        String paymentKey,
        String code,
        String message,
        Long amount,
        String rawPayload
){
    public PaymentFailCommand toCommand() {
        return new PaymentFailCommand(orderId, paymentKey, code, message, amount, rawPayload);
    }
}