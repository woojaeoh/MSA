package com.example.demo.settlement.application.usecase;

import com.example.demo.settlement.application.dto.SettlementLineResponse;

import java.time.LocalDate;
import java.util.List;

// Controller는 Service를 직접 모르고 이 인터페이스만 바라봄
// → 나중에 Service 구현체를 바꿔도 Controller는 수정 불필요
public interface SettlementUseCase {

    List<SettlementLineResponse> findBySettlementDate(LocalDate settlementDate);
}
