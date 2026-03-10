package com.example.demo.settlement.application.service;

import com.example.demo.settlement.application.dto.SettlementLineResponse;
import com.example.demo.settlement.application.usecase.SettlementUseCase;
import com.example.demo.settlement.domain.repository.SettlementLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SettlementService implements SettlementUseCase {

    // JpaRepository가 아닌 도메인 Repository 인터페이스에만 의존
    // → Service는 DB가 PostgreSQL인지, MongoDB인지 전혀 몰라도 됨
    private final SettlementLineRepository settlementLineRepository;

    @Override
    public List<SettlementLineResponse> findBySettlementDate(LocalDate settlementDate) {
        return settlementLineRepository.findBySettlementDate(settlementDate)
                .stream()
                .map(SettlementLineResponse::from)
                .toList();
    }
}
