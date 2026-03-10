package com.example.demo.settlement.application.dto;

import com.example.demo.batch.domain.model.SettlementLine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// 도메인 엔티티(SettlementLine)를 외부에 노출할 때 변환하는 DTO
// → 엔티티 내부 구조가 바뀌어도 이 DTO만 수정하면 API 스펙은 유지 가능
public record SettlementLineResponse(
        UUID id,
        LocalDate settlementDate,
        UUID orderId,
        String orderNo,
        UUID sellerId,
        BigDecimal grossAmount,
        BigDecimal feeAmount,
        BigDecimal refundAmount,
        BigDecimal netAmount,
        LocalDateTime paidAt,
        LocalDateTime createdAt
) {
    public static SettlementLineResponse from(SettlementLine line) {
        return new SettlementLineResponse(
                line.getId(),
                line.getSettlementDate(),
                line.getOrderId(),
                line.getOrderNo(),
                line.getSellerId(),
                line.getGrossAmount(),
                line.getFeeAmount(),
                line.getRefundAmount(),
                line.getNetAmount(),
                line.getPaidAt(),
                line.getCreatedAt()
        );
    }
}
