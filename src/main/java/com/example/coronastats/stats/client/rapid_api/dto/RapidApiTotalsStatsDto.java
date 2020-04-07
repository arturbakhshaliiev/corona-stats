package com.example.coronastats.stats.client.rapid_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class RapidApiTotalsStatsDto {

    private Long confirmed;
    private Long recovered;
    private Long critical;
    private Long deaths;
}
