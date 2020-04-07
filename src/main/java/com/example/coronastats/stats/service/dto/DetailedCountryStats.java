package com.example.coronastats.stats.service.dto;

import com.example.coronastats.stats.client.dto.CountryStatsDto;
import com.example.coronastats.stats.client.dto.TotalStatsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class DetailedCountryStats {

    private String country;
    private Long confirmed;
    private Long recovered;
    private Long critical;
    private Long deaths;
    private Long closed;
    private Double recoveredFromTotalPercentage;
    private Double recoveredFromClosedPercentage;
    private Double deathsFromTotalPercentage;
    private Double deathsFromClosedPercentage;

    public static DetailedCountryStats fromCountryStats(CountryStatsDto countryStats) {
        Long closed = countryStats.getRecovered() + countryStats.getDeaths();

        return DetailedCountryStats.builder()
                .country(countryStats.getCountry())
                .confirmed(countryStats.getConfirmed())
                .recovered(countryStats.getRecovered())
                .critical(countryStats.getCritical())
                .deaths(countryStats.getDeaths())
                .closed(closed)
                .recoveredFromTotalPercentage(countryStats.getRecovered().doubleValue() / countryStats.getConfirmed()
                        * 100)
                .recoveredFromClosedPercentage(countryStats.getRecovered().doubleValue() / closed * 100)
                .deathsFromTotalPercentage(countryStats.getDeaths().doubleValue() / countryStats.getConfirmed() * 100)
                .deathsFromClosedPercentage(countryStats.getDeaths().doubleValue() / closed * 100)
                .build();
    }
}
