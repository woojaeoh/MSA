package com.example.demo.settlement.domain.repository;

import com.example.demo.batch.domain.model.SettlementLine;

import java.time.LocalDate;
import java.util.List;

// 도메인이 "무엇을 할 수 있는지"만 선언 - JPA, DB는 전혀 모름
public interface SettlementLineRepository {

    SettlementLine save(SettlementLine settlementLine);

    List<SettlementLine> saveAll(List<SettlementLine> lines);

    List<SettlementLine> findBySettlementDate(LocalDate settlementDate);
}
