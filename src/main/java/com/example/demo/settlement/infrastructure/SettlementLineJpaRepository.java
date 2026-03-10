package com.example.demo.settlement.infrastructure;

import com.example.demo.batch.domain.model.SettlementLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SettlementLineJpaRepository extends JpaRepository<SettlementLine, UUID> {

    List<SettlementLine> findBySettlementDate(LocalDate settlementDate);
}
