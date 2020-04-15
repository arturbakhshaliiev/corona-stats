package com.example.coronastats.stats.repository;

import com.example.coronastats.stats.model.StatsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatsHistoryRepository extends JpaRepository<StatsHistory, Long>,
        JpaSpecificationExecutor<StatsHistory> {
}
