package com.example.coronastats.stats.service;

import com.example.coronastats.stats.client.dto.CountryStatsDto;
import com.example.coronastats.stats.service.dto.DetailedCountryStats;
import com.example.coronastats.stats.service.dto.DetailedTotalStats;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface StatsService {

    DetailedTotalStats getDetailedTotalStats() throws UnirestException;

    DetailedCountryStats getDetailedCountryStats(String countryName) throws UnirestException;
}
