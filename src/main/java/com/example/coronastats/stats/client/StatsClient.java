package com.example.coronastats.stats.client;

import com.example.coronastats.stats.client.dto.CountryStatsDto;
import com.example.coronastats.stats.client.dto.TotalStatsDto;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface StatsClient {

    TotalStatsDto getTotalStats() throws UnirestException;

    CountryStatsDto getCountryStats(String countryName) throws UnirestException;

    List<CountryStatsDto> getAllCountriesStats() throws UnirestException;
}
