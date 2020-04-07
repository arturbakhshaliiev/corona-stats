package com.example.coronastats.stats.service;

import com.example.coronastats.stats.service.dto.DetailedCountryStats;
import com.example.coronastats.stats.service.dto.DetailedTotalStats;
import com.example.coronastats.telegram.bot.Actions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.coronastats.telegram.bot.Actions.*;

@Data @AllArgsConstructor @Builder(toBuilder = true)
public class StatsUtil {

    private static DecimalFormat CUTTED_DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static String formatTotalStats(ResourceBundle rb, DetailedTotalStats stats) {
        return String.format("%s " + rb.getString("world") + "\n\n" +
                        "%s " + rb.getString("sick") + ": %s\n\n" +
                        "%s " + rb.getString("recovered") + ": %s (%s%% " + rb.getString("from_all_cases") + ")\n\n" +
                        "%s " + rb.getString("critical") + ": %s\n\n" +
                        "%s " + rb.getString("deaths") + ": %s (%s%% " + rb.getString("from_all_cases") + ", %s%% " +
                        rb.getString("from_closed_cases") + ")\n\n",
                "\uD83C\uDF0D", "\uD83C\uDF21",
                toSpaceNumber(stats.getConfirmed()),
                "✅", toSpaceNumber(stats.getRecovered()),
                CUTTED_DECIMAL_FORMAT.format(stats.getRecoveredFromTotalPercentage()),
                "\uD83D\uDE91", toSpaceNumber(stats.getCritical()),
                "\uD83C\uDF97", toSpaceNumber(stats.getDeaths()),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromTotalPercentage()),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromClosedPercentage()));
    }

    public static String formatCountryStats(ResourceBundle rb, DetailedCountryStats stats) {
        return String.format("%s %s\n\n" +
                        "%s" + ": %s\n\n" +
                        "%s" + ": %s (%s%% %s)\n\n" +
                        "%s" + ": %s\n\n" +
                        "%s" + ": %s (%s%% %s, %s%% %s)\n\n",
                "\uD83D\uDD0D ", stats.getCountry(),
                "\uD83C\uDF21 ", toSpaceNumber(stats.getConfirmed()),
                "✅ ", toSpaceNumber(stats.getRecovered()),
                CUTTED_DECIMAL_FORMAT.format(stats.getRecoveredFromTotalPercentage()), rb.getString("from_all_cases"),
                "\uD83D\uDE91 ", toSpaceNumber(stats.getCritical()),
                "\uD83C\uDF97 ", toSpaceNumber(stats.getDeaths()),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromTotalPercentage()), rb.getString("from_all_cases"),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromClosedPercentage()), rb.getString("from_closed_cases"));
    }

    private static String toSpaceNumber(Long number) {
        return new StringBuilder()
                .append(String.join(" ",
                        Iterables.toArray(Splitter.fixedLength(3).split(new StringBuilder()
                        .append(number)
                        .reverse()),
                        String.class)))
                .reverse()
                .toString();
    }

    private static ConcurrentHashMap<String, String> countryNames = new ConcurrentHashMap<String, String>() {
        {
            put(ALAND_ISLANDS, "Aland Islands");
            put(ALBANIA, "Albania");
            put(ANDORRA, "Andorra");
            put(AUSTRIA, "Austria");
            put(BELARUS, "Belarus");
            put(BELGIUM, "Belgium");
            put(BOSNIA_AND_HERZEGOVINA, "Bosnia and Herzegovina");
            put(BULGARIA, "Bulgaria");
            put(CHANNEL_ISLANDS, "Channel Islands");
            put(CROATIA, "Croatia");
            put(CYPRUS, "Cyprus");
            put(CZECHIA, "Czechia");
            put(DENMARK, "Denmark");
            put(ESTONIA, "Estonia");
            put(FAEROE_ISLANDS, "Faeroe Islands");
            put(FALKLAND_ISLANDS, "Falkland Islands");
            put(FINLAND, "Finland");
            put(FRANCE, "France");
            put(GERMANY, "Germany");
            put(GIBRALTAR, "Gibraltar");
            put(GREECE, "Greece");
            put(GREENLAND, "Greenland");
            put(GUERNSEY, "Guernsey");
            put(HUNGARY, "Hungary");
            put(ICELAND, "Iceland");
            put(IRELAND, "Ireland");
            put(ISLE_OF_MAN, "Isle of Man");
            put(ITALY, "Italy");
            put(JERSEY, "Jersey");
            put(LATVIA, "Latvia");
            put(LIECHTENSTEIN, "Liechtenstein");
            put(LITHUANIA, "Lithuania");
            put(LUXEMBOURG, "Luxembourg");
            put(MALTA, "Malta");
            put(MOLDOVA, "Moldova");
            put(MONACO, "Monaco");
            put(MONTENEGRO, "Montenegro");
            put(NORTH_MACEDONIA, "North Macedonia");
            put(NORWAY, "Norway");
            put(ROMANIA, "Romania");
            put(RUSSIA, "Russia");
            put(POLAND, "Poland");
            put(SAN_MARINO, "San Marino");
            put(SERBIA, "Serbia");
            put(SLOVAKIA, "Slovakia");
            put(SLOVENIA, "Slovenia");
            put(SPAIN, "Spain");
            put(SVALBARD, "Svalbard and Jan Mayen");
            put(SWEDEN, "Sweden");
            put(SWITZERLAND, "Switzerland");
            put(UK, "UK");
            put(UKRAINE, "Ukraine");
            put(VATICAN, "Vatican City");
        }
    };

    public static String getCountryName(String actionName) {
        return countryNames.get(actionName);
    }
}
