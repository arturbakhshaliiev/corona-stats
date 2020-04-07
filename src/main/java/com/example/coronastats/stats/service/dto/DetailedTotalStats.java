package com.example.coronastats.stats.service.dto;

import com.example.coronastats.stats.client.dto.TotalStatsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class DetailedTotalStats {

    private Long confirmed;
    private Long recovered;
    private Long critical;
    private Long deaths;
    private Long closed;
    private Double recoveredFromTotalPercentage;
    private Double recoveredFromClosedPercentage;
    private Double deathsFromTotalPercentage;
    private Double deathsFromClosedPercentage;

    public static DetailedTotalStats fromTotalStats(TotalStatsDto totalStats) {
        Long closed = totalStats.getRecovered() + totalStats.getDeaths();

        return DetailedTotalStats.builder()
                .confirmed(totalStats.getConfirmed())
                .recovered(totalStats.getRecovered())
                .critical(totalStats.getCritical())
                .deaths(totalStats.getDeaths())
                .closed(closed)
                .recoveredFromTotalPercentage(totalStats.getRecovered().doubleValue() / totalStats.getConfirmed() * 100)
                .recoveredFromClosedPercentage(totalStats.getRecovered().doubleValue() / closed * 100)
                .deathsFromTotalPercentage(totalStats.getDeaths().doubleValue() / totalStats.getConfirmed() * 100)
                .deathsFromClosedPercentage(totalStats.getDeaths().doubleValue() / closed * 100)
                .build();
    }
}
