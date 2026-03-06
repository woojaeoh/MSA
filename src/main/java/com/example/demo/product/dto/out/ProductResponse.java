package com.example.demo.product.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "상품 정보")
public record ProductResponse(
        @Schema
        UUID id,
        UUID sellerId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        LocalDateTime modifyDt


) {
}
