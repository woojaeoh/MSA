package com.example.demo.order.presentation.controller;

import com.example.demo.order.presentation.request.CreateOrderRequest;
import com.example.demo.order.presentation.response.OrderResponse;
import com.example.demo.order.usecase.OrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "주문 정보 API")
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    @Operation(summary = "주문 생성")
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderUseCase.create(request));
    }

    @GetMapping
    @Operation(summary = "주문 전체 조회")
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderUseCase.findAll());
    }

    @GetMapping("/settlement-candidates")
    @Operation(summary = "정산 대상 주문 조회")
    public ResponseEntity<List<OrderResponse>> findSettlementCandidates(
            @Parameter (description = "정산일(yyyy-mm-dd), 미 입력 시 오늘")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDate settlementDate
    ){
        return ResponseEntity.ok(orderUseCase.findSettlementCandidates(settlementDate));
    }
}
