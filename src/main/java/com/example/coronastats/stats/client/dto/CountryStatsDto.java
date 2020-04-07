package com.example.coronastats.stats.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class CountryStatsDto {

    private String country;
    private Long confirmed;
    private Long recovered;
    private Long critical;
    private Long deaths;
    private Double latitude;
    private Double longitude;
}

