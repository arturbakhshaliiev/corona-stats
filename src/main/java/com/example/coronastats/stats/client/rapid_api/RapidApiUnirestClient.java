package com.example.coronastats.stats.client.rapid_api;

import com.example.coronastats.stats.client.StatsClient;
import com.example.coronastats.stats.client.dto.CountryStatsDto;
import com.example.coronastats.stats.client.dto.TotalStatsDto;
import com.example.coronastats.stats.client.rapid_api.dto.RapidApiCountryStatsDto;
import com.example.coronastats.stats.client.rapid_api.dto.RapidApiTotalsStatsDto;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RapidApiUnirestClient implements StatsClient {

    private String apiUrl;
    @Value("${rapid-api.apiHost}")
    private String apiHost;
    @Value("${rapid-api.apiKey}")
    private String apiKey;

    private Gson gson = new Gson();

    @PostConstruct
    private void setup() {
        apiUrl = "https://" + apiHost;
    }

    @Override
    public TotalStatsDto getTotalStats() throws UnirestException {
        RapidApiTotalsStatsDto totalsDto = gson.fromJson(Unirest.get(apiUrl + "/totals")
                .header("x-rapidapi-host", apiHost)
                .header("x-rapidapi-key", apiKey)
                .queryString("format", "undefined")
                .asJson().getBody().toString(), RapidApiTotalsStatsDto[].class)[0];

        return TotalStatsDto.builder()
                .confirmed(totalsDto.getConfirmed())
                .recovered(totalsDto.getRecovered())
                .critical(totalsDto.getCritical())
                .deaths(totalsDto.getDeaths())
                .build();
    }

    @Override
    public CountryStatsDto getCountryStats(String countryName) throws UnirestException {
        RapidApiCountryStatsDto countryDto = gson.fromJson(Unirest.get(apiUrl + "/country")
                .header("x-rapidapi-host", apiHost)
                .header("x-rapidapi-key", apiKey)
                .queryString("format", "undefined")
                .queryString("name", countryName)
                .asJson().getBody().toString(), RapidApiCountryStatsDto[].class)[0];

        return toCountryStatsDto(countryDto);
    }

    @Override
    public List<CountryStatsDto> getAllCountriesStats() throws UnirestException {
        RapidApiCountryStatsDto[] allCountriesStats =  gson.fromJson(Unirest.get(apiUrl + "/country/all")
                .header("x-rapidapi-host", apiHost)
                .header("x-rapidapi-key", apiKey)
                .queryString("format", "undefined")
                .asJson().getBody().toString(), RapidApiCountryStatsDto[].class);

        return Arrays.stream(allCountriesStats).map(this::toCountryStatsDto).collect(Collectors.toList());
    }

    private CountryStatsDto toCountryStatsDto(RapidApiCountryStatsDto rapidApiCountryStatsDto) {
        return CountryStatsDto.builder()
                .country(rapidApiCountryStatsDto.getCountry())
                .confirmed(rapidApiCountryStatsDto.getConfirmed())
                .recovered(rapidApiCountryStatsDto.getRecovered())
                .critical(rapidApiCountryStatsDto.getCritical())
                .deaths(rapidApiCountryStatsDto.getDeaths())
                .latitude(rapidApiCountryStatsDto.getLatitude())
                .longitude(rapidApiCountryStatsDto.getLongitude())
                .build();
    }
}
