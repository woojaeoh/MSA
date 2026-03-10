package com.example.demo.batch.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "settlement_line",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_settlement_line_settlement_date_order_id",
                        columnNames = {"settlement_date", "order_id"}
                )
        }
)
@Getter
@Comment("정산 라인 엔티티")
public class SettlementLine {

    @Id
    @Column(nullable = false)
    @Comment("정산 라인 ID(UUID)")
    private UUID id;

    @Column(name = "settlement_date", nullable = false)
    @Comment("정산 기준 일자")
    private LocalDate settlementDate;

    @Column(name = "order_id", nullable = false)
    @Comment("주문 ID(UUID)")
    private UUID orderId;

    @Column(name = "order_no", nullable = false, length = 50)
    @Comment("주문 번호")
    private String orderNo;

    @Column(name = "seller_id", nullable = false)
    @Comment("판매자 회원 ID(UUID)")
    private UUID sellerId;

    @Column(name = "gross_amount", nullable = false, precision = 15, scale = 2)
    @Comment("총 주문 금액")
    private BigDecimal grossAmount;

    @Column(name = "fee_amount", nullable = false, precision = 15, scale = 2)
    @Comment("수수료 금액")
    private BigDecimal feeAmount;

    @Column(name = "refund_amount", nullable = false, precision = 15, scale = 2)
    @Comment("환불 금액")
    private BigDecimal refundAmount;

    @Column(name = "net_amount", nullable = false, precision = 15, scale = 2)
    @Comment("순 정산 금액")
    private BigDecimal netAmount;

    @Column(name = "paid_at", nullable = false)
    @Comment("결제 완료 시각")
    private LocalDateTime paidAt;

    @Column(name = "created_at", nullable = false)
    @Comment("정산 라인 생성 시각")
    private LocalDateTime createdAt;

    protected SettlementLine() {}

    // 외부에서 new 직접 못 하게 막고, 반드시 이 메서드로만 생성
    // → 생성 규칙(createdAt 자동 세팅 등)이 도메인 안에서 관리됨
    public static SettlementLine create(
            LocalDate settlementDate,
            UUID orderId,
            String orderNo,
            UUID sellerId,
            BigDecimal grossAmount,
            BigDecimal feeAmount,
            BigDecimal refundAmount,
            BigDecimal netAmount,
            LocalDateTime paidAt
    ) {
        SettlementLine line = new SettlementLine();
        line.id = UUID.randomUUID();
        line.settlementDate = settlementDate;
        line.orderId = orderId;
        line.orderNo = orderNo;
        line.sellerId = sellerId;
        line.grossAmount = grossAmount;
        line.feeAmount = feeAmount;
        line.refundAmount = refundAmount;
        line.netAmount = netAmount;
        line.paidAt = paidAt;
        line.createdAt = LocalDateTime.now();
        return line;
    }
}
