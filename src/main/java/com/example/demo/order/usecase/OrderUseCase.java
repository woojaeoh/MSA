package com.example.demo.order.usecase;

import com.example.demo.order.presentation.request.CreateOrderRequest;
import com.example.demo.order.presentation.response.OrderResponse;

import java.time.LocalDate;
import java.util.List;

public interface OrderUseCase {

    OrderResponse create(CreateOrderRequest request);

    List<OrderResponse> findAll();

    List<OrderResponse> findSettlementCandidates(LocalDate settlementDate);
}
