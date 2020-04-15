package com.example.coronastats.stats.service;

import com.example.coronastats.stats.model.StatsHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StatsHistoryService {

    Page<StatsHistory> list(StatsHistoryFilter statsHistoryFilter, Pageable pageable);

    List<StatsHistory> list(StatsHistoryFilter statsHistoryFilter, Sort sort);

    StatsHistory getOne(StatsHistoryFilter statsHistoryFilter);

    StatsHistory add(StatsHistory statsHistory);

    StatsHistory update(StatsHistory statsHistory);
}
