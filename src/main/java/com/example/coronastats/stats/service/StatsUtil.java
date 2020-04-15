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
import java.util.Arrays;
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
                        "%s" + rb.getString("sick") + ": %s\n\n" +
                        "%s" + rb.getString("recovered") + ": %s (%s%% %s)\n\n" +
                        "%s" + rb.getString("critical") + ": %s\n\n" +
                        "%s" + rb.getString("deaths") + ": %s (%s%% %s, %s%% %s)\n\n",
                "\uD83D\uDD0D ", rb.getString(countryNameToResource(stats.getCountry())),
                "\uD83C\uDF21 ", toSpaceNumber(stats.getConfirmed()),
                "✅ ", toSpaceNumber(stats.getRecovered()),
                CUTTED_DECIMAL_FORMAT.format(stats.getRecoveredFromTotalPercentage()), rb.getString("from_all_cases"),
                "\uD83D\uDE91 ", toSpaceNumber(stats.getCritical()),
                "\uD83C\uDF97 ", toSpaceNumber(stats.getDeaths()),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromTotalPercentage()), rb.getString("from_all_cases"),
                CUTTED_DECIMAL_FORMAT.format(stats.getDeathsFromClosedPercentage()), rb.getString("from_closed_cases"));
    }

    public static String getContinent(String countryAction) {
        return Arrays.asList(ALAND_ISLANDS, ALBANIA, ANDORRA, AUSTRIA, BELARUS, BELGIUM, BOSNIA_AND_HERZEGOVINA,
                BULGARIA, CROATIA, CYPRUS, CZECHIA, DENMARK, ESTONIA, FAEROE_ISLANDS, FINLAND, FRANCE, GERMANY,
                GIBRALTAR, GREECE, GUERNSEY, HUNGARY, ICELAND, IRELAND, ISLE_OF_MAN, ITALY, JERSEY, LATVIA,
                LIECHTENSTEIN, LITHUANIA, LUXEMBOURG, NORTH_MACEDONIA, MALTA, MOLDOVA, MONACO, MONTENEGRO, NETHERLANDS,
                NORWAY, POLAND, PORTUGAL, ROMANIA, RUSSIA, SAN_MARINO, SERBIA, SLOVAKIA, SLOVENIA, SPAIN,
                SVALBARD_AND_JAN_MAYEN, SWEDEN, SWITZERLAND, UKRAINE, UK, VATICAN_CITY).contains(countryAction)
                ? EUROPE :
                Arrays.asList(AFGHANISTAN, ARMENIA, AZERBAIJAN, BAHRAIN, BANGLADESH, BHUTAN,
                        BRITISH_INDIAN_OCEAN_TERRITORY, BRUNEI, CAMBODIA, CHINA, CHRISTMAS_ISLAND,
                        COCOS_KEELING_ISLANDS, GEORGIA, HONG_KONG, INDIA, INDONESIA, IRAN, IRAQ, ISRAEL, JAPAN, JORDAN,
                        KAZAKHSTAN, KUWAIT, KYRGYZSTAN, LAOS, LEBANON, MACAO, MALAYSIA, MALDIVES, MONGOLIA, MYANMAR,
                        NEPAL, N_KOREA, OMAN, PAKISTAN, PALESTINE, PHILIPPINES, QATAR, SAUDI_ARABIA, SINGAPORE,
                        S_KOREA, SRI_LANKA, SYRIA, TAIWAN, TAJIKISTAN, THAILAND, TURKEY, TURKMENISTAN, UAE, UZBEKISTAN,
                        VIETNAM, YEMEN).contains(countryAction)
                ? ASIA :
                Arrays.asList(ANGUILLA, ANTIGUA_AND_BARBUDA, ARUBA, BAHAMAS, BARBADOS, BELIZE, BERMUDA,
                        BONAIRE_SINT_EUSTATIUS_AND_SABA, BRITISH_VIRGIN_ISLANDS, CANADA, CAYMAN_ISLANDS, COSTA_RICA,
                        CUBA, CURAÇAO, DOMINICA, DOMINICAN_REPUBLIC, EL_SALVADOR, GREENLAND, GRENADA, GUADELOUPE,
                        GUATEMALA, HAITI, HONDURAS, JAMAICA, MARTINIQUE, MEXICO, MONTSERRAT, NICARAGUA, PANAMA,
                        PUERTO_RICO, ST_BARTH, SAINT_KITTS_AND_NEVIS, SAINT_LUCIA, SAINT_MARTIN,
                        SAINT_PIERRE_AND_MIQUELON, ST_VINCENT_GRENADINES, SINT_MAARTEN, TRINIDAD_AND_TOBAGO,
                        TURKS_AND_CAICOS, US_VIRGIN_ISLANDS, USA).contains(countryAction)
                ? NORTH_AMERICA :
                Arrays.asList(ARGENTINA, BOLIVIA, BRAZIL, CHILE, COLOMBIA, ECUADOR, FALKLAND_ISLANDS_MALVINAS,
                        FRENCH_GUIANA, GUYANA, PARAGUAY, PERU, SURINAME, URUGUAY, VENEZUELA).contains(countryAction)
                ? SOUTH_AMERICA :
                Arrays.asList(ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA_FASO, BURUNDI, CAMEROON, CABO_VERDE,
                        CAR, CHAD, COMOROS, DRC, DJIBOUTI, EGYPT, EQUATORIAL_GUINEA, ERITREA, ETHIOPIA, GABON, GAMBIA,
                        GHANA, GUINEA, GUINEA_BISSAU, IVORY_COAST, KENYA, LESOTHO, LIBERIA, LIBYA, MADAGASCAR, MALAWI,
                        MALI, MAURITANIA, MAURITIUS, MAYOTTE, MOROCCO, MOZAMBIQUE, NAMIBIA, NIGER, NIGERIA, CONGO,
                        REUNION, RWANDA, SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA, SAO_TOME_AND_PRINCIPE, SENEGAL,
                        SEYCHELLES, SIERRA_LEONE, SOMALIA, SOUTH_AFRICA, SOUTH_SUDAN, SUDAN, ESWATINI, TANZANIA, TOGO,
                        TUNISIA, UGANDA, WESTERN_SAHARA, ZAMBIA, ZIMBABWE).contains(countryAction)
                ? AFRICA :
                Arrays.asList(AMERICAN_SAMOA, AUSTRALIA, COOK_ISLANDS, TIMOR_LESTE, FIJI, FRENCH_POLYNESIA, GUAM,
                        KIRIBATI, MARSHALL_ISLANDS, MICRONESIA, NAURU, NEW_CALEDONIA, NEW_ZEALAND, NIUE, NORFOLK_ISLAND,
                        NORTHERN_MARIANA_ISLANDS, PALAU, PAPUA_NEW_GUINEA, PITCAIRN, SAMOA, SOLOMON_ISLANDS, TOKELAU,
                        TONGA, TUVALU, UNITED_STATES_MINOR_OUTLYING_ISLANDS, VANUATU, WALLIS_AND_FUTUNA).contains(countryAction)
                ? AUSTRALIA_AND_OCEANIA
                : START;
    }

    private static String countryNameToResource(String countryName) {
        return countryName.replace(".", "").replace(",", "")
                .replace("(", "").replace(")", "").replace(" ", "_")
                .replace("-", "_").toLowerCase();
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
            put(AFGHANISTAN, "Afghanistan");
            put(ALAND_ISLANDS, "Aland Islands");
            put(ALBANIA, "Albania");
            put(ALGERIA, "Algeria");
            put(AMERICAN_SAMOA, "American Samoa");
            put(ANDORRA, "Andorra");
            put(ANGOLA, "Angola");
            put(ANGUILLA, "Anguilla");
            put(ANTARCTICA, "Antarctica");
            put(ANTIGUA_AND_BARBUDA, "Antigua and Barbuda");
            put(ARGENTINA, "Argentina");
            put(ARMENIA, "Armenia");
            put(ARUBA, "Aruba");
            put(AUSTRALIA, "Australia");
            put(AUSTRIA, "Austria");
            put(AZERBAIJAN, "Azerbaijan");
            put(BAHAMAS, "Bahamas");
            put(BAHRAIN, "Bahrain");
            put(BANGLADESH, "Bangladesh");
            put(BARBADOS, "Barbados");
            put(BELARUS, "Belarus");
            put(BELGIUM, "Belgium");
            put(BELIZE, "Belize");
            put(BENIN, "Benin");
            put(BERMUDA, "Bermuda");
            put(BHUTAN, "Bhutan");
            put(BOLIVIA, "Bolivia");
            put(BONAIRE_SINT_EUSTATIUS_AND_SABA, "Bonaire, Sint Eustatius and Saba");
            put(BOSNIA_AND_HERZEGOVINA, "Bosnia and Herzegovina");
            put(BOTSWANA, "Botswana");
            put(BOUVET_ISLAND, "Bouvet Island");
            put(BRAZIL, "Brazil");
            put(BRITISH_INDIAN_OCEAN_TERRITORY, "British Indian Ocean Territory");
            put(BRITISH_VIRGIN_ISLANDS, "British Virgin Islands");
            put(BRUNEI, "Brunei");
            put(BULGARIA, "Bulgaria");
            put(BURKINA_FASO, "Burkina Faso");
            put(BURUNDI, "Burundi");
            put(CABO_VERDE, "Cabo Verde");
            put(CAMBODIA, "Cambodia");
            put(CAMEROON, "Cameroon");
            put(CANADA, "Canada");
            put(CAR, "CAR");
            put(CARIBBEAN_NETHERLANDS, "Caribbean Netherlands");
            put(CAYMAN_ISLANDS, "Cayman Islands");
            put(CHAD, "Chad");
            put(CHANNEL_ISLANDS, "Channel Islands");
            put(CHILE, "Chile");
            put(CHINA, "China");
            put(CHRISTMAS_ISLAND, "Christmas Island");
            put(COCOS_KEELING_ISLANDS, "Cocos (Keeling) Islands");
            put(COLOMBIA, "Colombia");
            put(COMOROS, "Comoros");
            put(CONGO, "Congo");
            put(COOK_ISLANDS, "Cook Islands");
            put(COSTA_RICA, "Costa Rica");
            put(CROATIA, "Croatia");
            put(CUBA, "Cuba");
            put(CURAÇAO, "Curaçao");
            put(CYPRUS, "Cyprus");
            put(CZECHIA, "Czechia");
            put(DENMARK, "Denmark");
            put(DIAMOND_PRINCESS, "Diamond Princess");
            put(DJIBOUTI, "Djibouti");
            put(DOMINICA, "Dominica");
            put(DOMINICAN_REPUBLIC, "Dominican Republic");
            put(DRC, "DRC");
            put(ECUADOR, "Ecuador");
            put(EGYPT, "Egypt");
            put(EL_SALVADOR, "El Salvador");
            put(EQUATORIAL_GUINEA, "Equatorial Guinea");
            put(ERITREA, "Eritrea");
            put(ESTONIA, "Estonia");
            put(ESWATINI, "Eswatini");
            put(ETHIOPIA, "Ethiopia");
            put(FAEROE_ISLANDS, "Faeroe Islands");
            put(FALKLAND_ISLANDS_MALVINAS, "Falkland Islands (Malvinas)");
            put(FIJI, "Fiji");
            put(FINLAND, "Finland");
            put(FRANCE, "France");
            put(FRENCH_GUIANA, "French Guiana");
            put(FRENCH_POLYNESIA, "French Polynesia");
            put(FRENCH_SOUTHERN_TERRITORIES, "French Southern Territories");
            put(GABON, "Gabon");
            put(GAMBIA, "Gambia");
            put(GEORGIA, "Georgia");
            put(GERMANY, "Germany");
            put(GHANA, "Ghana");
            put(GIBRALTAR, "Gibraltar");
            put(GREECE, "Greece");
            put(GREENLAND, "Greenland");
            put(GRENADA, "Grenada");
            put(GUADELOUPE, "Guadeloupe");
            put(GUAM, "Guam");
            put(GUATEMALA, "Guatemala");
            put(GUERNSEY, "Guernsey");
            put(GUINEA, "Guinea");
            put(GUINEA_BISSAU, "Guinea-Bissau");
            put(GUYANA, "Guyana");
            put(HAITI, "Haiti");
            put(HEARD_ISLAND_AND_MCDONALD_ISLANDS, "Heard Island and Mcdonald Islands");
            put(HONDURAS, "Honduras");
            put(HONG_KONG, "Hong Kong");
            put(HUNGARY, "Hungary");
            put(ICELAND, "Iceland");
            put(INDIA, "India");
            put(INDONESIA, "Indonesia");
            put(IRAN, "Iran");
            put(IRAQ, "Iraq");
            put(IRELAND, "Ireland");
            put(ISLE_OF_MAN, "Isle of Man");
            put(ISRAEL, "Israel");
            put(ITALY, "Italy");
            put(IVORY_COAST, "Ivory Coast");
            put(JAMAICA, "Jamaica");
            put(JAPAN, "Japan");
            put(JERSEY, "Jersey");
            put(JORDAN, "Jordan");
            put(KAZAKHSTAN, "Kazakhstan");
            put(KENYA, "Kenya");
            put(KIRIBATI, "Kiribati");
            put(KUWAIT, "Kuwait");
            put(KYRGYZSTAN, "Kyrgyzstan");
            put(LAOS, "Laos");
            put(LATVIA, "Latvia");
            put(LEBANON, "Lebanon");
            put(LESOTHO, "Lesotho");
            put(LIBERIA, "Liberia");
            put(LIBYA, "Libya");
            put(LIECHTENSTEIN, "Liechtenstein");
            put(LITHUANIA, "Lithuania");
            put(LUXEMBOURG, "Luxembourg");
            put(MACAO, "Macao");
            put(MADAGASCAR, "Madagascar");
            put(MALAWI, "Malawi");
            put(MALAYSIA, "Malaysia");
            put(MALDIVES, "Maldives");
            put(MALI, "Mali");
            put(MALTA, "Malta");
            put(MARSHALL_ISLANDS, "Marshall Islands");
            put(MARTINIQUE, "Martinique");
            put(MAURITANIA, "Mauritania");
            put(MAURITIUS, "Mauritius");
            put(MAYOTTE, "Mayotte");
            put(MEXICO, "Mexico");
            put(MICRONESIA, "Micronesia");
            put(MOLDOVA, "Moldova");
            put(MONACO, "Monaco");
            put(MONGOLIA, "Mongolia");
            put(MONTENEGRO, "Montenegro");
            put(MONTSERRAT, "Montserrat");
            put(MOROCCO, "Morocco");
            put(MOZAMBIQUE, "Mozambique");
            put(MS_ZAANDAM, "MS Zaandam");
            put(MYANMAR, "Myanmar");
            put(N_KOREA, "N. Korea");
            put(NAMIBIA, "Namibia");
            put(NAURU, "Nauru");
            put(NEPAL, "Nepal");
            put(NETHERLANDS, "Netherlands");
            put(NEW_CALEDONIA, "New Caledonia");
            put(NEW_ZEALAND, "New Zealand");
            put(NICARAGUA, "Nicaragua");
            put(NIGER, "Niger");
            put(NIGERIA, "Nigeria");
            put(NIUE, "Niue");
            put(NORFOLK_ISLAND, "Norfolk Island");
            put(NORTH_MACEDONIA, "North Macedonia");
            put(NORTHERN_MARIANA_ISLANDS, "Northern Mariana Islands");
            put(NORWAY, "Norway");
            put(OMAN, "Oman");
            put(PAKISTAN, "Pakistan");
            put(PALAU, "Palau");
            put(PALESTINE, "Palestine");
            put(PANAMA, "Panama");
            put(PAPUA_NEW_GUINEA, "Papua New Guinea");
            put(PARAGUAY, "Paraguay");
            put(PERU, "Peru");
            put(PHILIPPINES, "Philippines");
            put(PITCAIRN, "Pitcairn");
            put(POLAND, "Poland");
            put(PORTUGAL, "Portugal");
            put(PUERTO_RICO, "Puerto Rico");
            put(QATAR, "Qatar");
            put(REUNION, "Reunion");
            put(ROMANIA, "Romania");
            put(RUSSIA, "Russia");
            put(RWANDA, "Rwanda");
            put(S_KOREA, "S. Korea");
            put(SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA, "Saint Helena, Ascension and Tristan da Cunha");
            put(SAINT_KITTS_AND_NEVIS, "Saint Kitts and Nevis");
            put(SAINT_LUCIA, "Saint Lucia");
            put(SAINT_MARTIN, "Saint Martin");
            put(SAINT_PIERRE_AND_MIQUELON, "Saint Pierre and Miquelon");
            put(SAMOA, "Samoa");
            put(SAN_MARINO, "San Marino");
            put(SAO_TOME_AND_PRINCIPE, "Sao Tome and Principe");
            put(SAUDI_ARABIA, "Saudi Arabia");
            put(SENEGAL, "Senegal");
            put(SERBIA, "Serbia");
            put(SEYCHELLES, "Seychelles");
            put(SIERRA_LEONE, "Sierra Leone");
            put(SINGAPORE, "Singapore");
            put(SINT_MAARTEN, "Sint Maarten");
            put(SLOVAKIA, "Slovakia");
            put(SLOVENIA, "Slovenia");
            put(SOLOMON_ISLANDS, "Solomon Islands");
            put(SOMALIA, "Somalia");
            put(SOUTH_AFRICA, "South Africa");
            put(SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS, "South Georgia and the South Sandwich Islands");
            put(SOUTH_SUDAN, "South Sudan");
            put(SPAIN, "Spain");
            put(SRI_LANKA, "Sri Lanka");
            put(ST_BARTH, "St. Barth");
            put(ST_VINCENT_GRENADINES, "St. Vincent Grenadines");
            put(SUDAN, "Sudan");
            put(SURINAME, "Suriname");
            put(SVALBARD_AND_JAN_MAYEN, "Svalbard and Jan Mayen");
            put(SWEDEN, "Sweden");
            put(SWITZERLAND, "Switzerland");
            put(SYRIA, "Syria");
            put(TAIWAN, "Taiwan");
            put(TAJIKISTAN, "Tajikistan");
            put(TANZANIA, "Tanzania");
            put(THAILAND, "Thailand");
            put(TIMOR_LESTE, "Timor-Leste");
            put(TOGO, "Togo");
            put(TOKELAU, "Tokelau");
            put(TONGA, "Tonga");
            put(TRINIDAD_AND_TOBAGO, "Trinidad and Tobago");
            put(TUNISIA, "Tunisia");
            put(TURKEY, "Turkey");
            put(TURKMENISTAN, "Turkmenistan");
            put(TURKS_AND_CAICOS, "Turks and Caicos");
            put(TUVALU, "Tuvalu");
            put(US_VIRGIN_ISLANDS, "U.S. Virgin Islands");
            put(UAE, "UAE");
            put(UGANDA, "Uganda");
            put(UK, "UK");
            put(UKRAINE, "Ukraine");
            put(UNITED_STATES_MINOR_OUTLYING_ISLANDS, "United States Minor Outlying Islands");
            put(URUGUAY, "Uruguay");
            put(USA, "USA");
            put(UZBEKISTAN, "Uzbekistan");
            put(VANUATU, "Vanuatu");
            put(VATICAN_CITY, "Vatican City");
            put(VENEZUELA, "Venezuela");
            put(VIETNAM, "Vietnam");
            put(WALLIS_AND_FUTUNA, "Wallis and Futuna");
            put(WESTERN_SAHARA, "Western Sahara");
            put(YEMEN, "Yemen");
            put(ZAMBIA, "Zambia");
            put(ZIMBABWE, "Zimbabwe");
        }
    };

    public static String getCountryName(String actionName) {
        return countryNames.get(actionName);
    }

    public static boolean isCountry(String actionName) {
        return countryNames.get(actionName) != null;
    }
}
