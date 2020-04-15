package com.example.coronastats.stats.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)
public class StatsHistoryFilter {

    private String country;
    private Date date;
}
