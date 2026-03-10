package com.example.demo.settlement.presentation;

import com.example.demo.settlement.application.dto.SettlementLineResponse;
import com.example.demo.settlement.application.usecase.SettlementUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
@Tag(name = "Settlement", description = "정산 조회 API")
public class SettlementController {

    // Service가 아닌 UseCase 인터페이스만 바라봄
    private final SettlementUseCase settlementUseCase;

    @GetMapping
    @Operation(summary = "정산 라인 조회", description = "정산일 기준으로 정산 라인 목록을 조회합니다.")
    public ResponseEntity<List<SettlementLineResponse>> findBySettlementDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate settlementDate
    ) {
        return ResponseEntity.ok(settlementUseCase.findBySettlementDate(settlementDate));
    }
}
