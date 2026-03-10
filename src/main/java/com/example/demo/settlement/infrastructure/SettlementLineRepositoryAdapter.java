package com.example.demo.settlement.infrastructure;

import com.example.demo.batch.domain.model.SettlementLine;
import com.example.demo.settlement.domain.repository.SettlementLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// 도메인이 선언한 계약(SettlementLineRepository)을 JPA로 실제 구현
// Service → SettlementLineRepository(인터페이스) ← 이 클래스가 구현체로 주입됨
@Repository
@RequiredArgsConstructor
public class SettlementLineRepositoryAdapter implements SettlementLineRepository {

    private final SettlementLineJpaRepository jpaRepository;

    @Override
    public SettlementLine save(SettlementLine settlementLine) {
        return jpaRepository.save(settlementLine);
    }

    @Override
    public List<SettlementLine> saveAll(List<SettlementLine> lines) {
        return jpaRepository.saveAll(lines);
    }

    @Override
    public List<SettlementLine> findBySettlementDate(LocalDate settlementDate) {
        return jpaRepository.findBySettlementDate(settlementDate);
    }
}
