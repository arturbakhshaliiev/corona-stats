package com.example.coronastats.stats.model;

import com.example.coronastats.stats.service.dto.DetailedCountryStats;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "corona_stats_history", uniqueConstraints = @UniqueConstraint(columnNames = {"country", "date"},
        name = "stats_history_country_date_constraint"))
@TableGenerator(name = "stats_sequence", table = "hibernate_stats_sequences", pkColumnValue = "stats_history",
        initialValue = 1, allocationSize = 1)
@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true) @ToString(onlyExplicitlyIncluded = true)
public class StatsHistory {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "stats_sequence")
    private Long id;

    @ToString.Include
    @NotNull
    private String country;

    @ToString.Include
    @NotNull
    @Temporal(DATE)
    private Date date;

    private Long confirmed;

    private Long recovered;

    private Long critical;

    private Long deaths;

    public static StatsHistory fromDetailedCountryStats(DetailedCountryStats detailedCountryStats) {
        return StatsHistory.builder()
                .country(detailedCountryStats.getCountry())
                .date(new Date())
                .confirmed(detailedCountryStats.getConfirmed())
                .recovered(detailedCountryStats.getRecovered())
                .critical(detailedCountryStats.getCritical())
                .deaths(detailedCountryStats.getDeaths())
                .build();
    }
}
