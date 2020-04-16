package com.example.coronastats.stats.service;

import com.example.coronastats.stats.client.StatsClient;
import com.example.coronastats.stats.client.dto.CountryStatsDto;
import com.example.coronastats.stats.service.dto.DetailedCountryStats;
import com.example.coronastats.stats.service.dto.DetailedTotalStats;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultStatsService implements StatsService {

    @Autowired
    private StatsClient statsClient;

    @Override
    public DetailedTotalStats getDetailedTotalStats() throws UnirestException {
        return DetailedTotalStats.fromTotalStats(statsClient.getTotalStats());
    }

    @Override
    public DetailedCountryStats getDetailedCountryStats(String countryName) throws UnirestException {
        return DetailedCountryStats.fromCountryStats(statsClient.getCountryStats(countryName));
    }

    @Override
    public List<DetailedCountryStats> getAllDetailedCountriesStats() throws UnirestException {
        return statsClient.getAllCountriesStats().stream()
                .map(DetailedCountryStats::fromCountryStats)
                .collect(Collectors.toList());
    }
}
