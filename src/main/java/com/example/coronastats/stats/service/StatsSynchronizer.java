package com.example.coronastats.stats.service;

import com.example.coronastats.stats.model.StatsHistory;
import com.example.coronastats.stats.service.dto.DetailedCountryStats;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StatsSynchronizer {

    @Autowired
    private StatsService statsService;

    @Autowired
    private StatsHistoryService statsHistoryService;

    @Value("${stats.refreshInterval:1200000}")
    private int refreshIntervalMillis;

    @Scheduled(fixedDelayString = "${stats.refreshInterval:1200000}")
    public void schedule() throws UnirestException {
        logger.info(String.format("Started Stats Synchronizer. Interval: '%d'", refreshIntervalMillis));
        refreshStatsHistory();
        logger.info(String.format("Finished Stats Synchronizer. Interval: '%d'", refreshIntervalMillis));
    }

    private void refreshStatsHistory() throws UnirestException {
        StatsHistoryFilter filter = StatsHistoryFilter.builder().date(DateUtils.truncate(new Date(), Calendar.DATE))
                .build();
        Map<String, StatsHistory> historyByCountries = statsHistoryService.list(filter, Sort.unsorted()).stream()
                .collect(Collectors.toMap(StatsHistory::getCountry, statsHistory -> statsHistory));
        List<DetailedCountryStats> detailedStats = statsService.getAllDetailedCountriesStats();
        detailedStats.forEach(stats -> {
            StatsHistory todayHistoryForCountry = historyByCountries.get(stats.getCountry());
            if (todayHistoryForCountry == null) {
                statsHistoryService.add(StatsHistory.fromDetailedCountryStats(stats));
            } else {
                statsHistoryService.update(StatsHistory.fromDetailedCountryStats(stats));
            }
        });
    }
}
