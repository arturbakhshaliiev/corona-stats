package com.example.coronastats.telegram.service;

import com.example.coronastats.telegram.bot.dto.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.coronastats.telegram.bot.Actions.*;
import static com.example.coronastats.telegram.bot.Actions.START;

@Slf4j
@Component
public class TelegramInterfaceHelper {

    @Autowired
    private TelegramUserService telegramUserService;

    public InlineKeyboardButton getButton(String text, String actionName) {
        return new InlineKeyboardButton()
                .setText(text)
                .setCallbackData(Action.builder().name(actionName).build().getName());
    }

    public String getGreetingText(User user) {
        return String.format("%s %s, %s.\n"
                + "%s " + "\n"
                + "%s %s: %s.",
                "\uD83D\uDC4B", getRb(user).getString("hello"), user.getFirstName(),
                getRb(user).getString("greeting"),
                "\uD83D\uDC65", getRb(user).getString("users"),
                telegramUserService.getUsersCount());
    }

    public InlineKeyboardButton getBackButton(User user, String command) {
        return getButton(" \uD83D\uDD19 " + getRb(user).getString("back"), command);
    }

    public InlineKeyboardMarkup getBackKeyboard(User user, String command) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(Arrays.asList(getBackButton(user, command))));
    }

    public InlineKeyboardMarkup getEuropeCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddfd" + translate(user, ALAND_ISLANDS), ALAND_ISLANDS),
                        getButton("\ud83c\udde6\ud83c\uddf1" + translate(user, ALBANIA), ALBANIA)),
                Arrays.asList(getButton("\ud83c\udde6\ud83c\udde9" + translate(user, ANDORRA), ANDORRA),
                        getButton("\ud83c\udde6\ud83c\uddf9" + translate(user, AUSTRIA), AUSTRIA)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddfe" + translate(user, BELARUS), BELARUS),
                        getButton("\ud83c\udde7\ud83c\uddea" + translate(user, BELGIUM), BELGIUM)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\udde6" + translate(user, BOSNIA_AND_HERZEGOVINA), BOSNIA_AND_HERZEGOVINA),
                        getButton("\ud83c\udde7\ud83c\uddec" + translate(user, BULGARIA), BULGARIA)),
                Arrays.asList(getButton("\ud83c\udded\ud83c\uddf7" + translate(user, CROATIA), CROATIA),
                        getButton("\ud83c\udde8\ud83c\uddfe" + translate(user, CYPRUS), CYPRUS)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddff" + translate(user, CZECHIA), CZECHIA),
                        getButton("\ud83c\udde9\ud83c\uddf0" + translate(user, DENMARK), DENMARK)),
                Arrays.asList(getButton("\ud83c\uddea\ud83c\uddea" + translate(user, ESTONIA), ESTONIA),
                        getButton("\ud83c\uddeb\ud83c\uddf4" + translate(user, FAEROE_ISLANDS), FAEROE_ISLANDS)),
                Arrays.asList(getButton("\ud83c\uddeb\ud83c\uddee" + translate(user, FINLAND), FINLAND),
                        getButton("\ud83c\uddeb\ud83c\uddf7" + translate(user, FRANCE), FRANCE)),
                Arrays.asList(getButton("\ud83c\udde9\ud83c\uddea" + translate(user, GERMANY), GERMANY),
                        getButton("\ud83c\uddec\ud83c\uddee" + translate(user, GIBRALTAR), GIBRALTAR)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddf7" + translate(user, GREECE), GREECE),
                        getButton("\ud83c\uddec\ud83c\uddec" + translate(user, GUERNSEY), GUERNSEY)),
                Arrays.asList(getButton("\ud83c\udded\ud83c\uddfa" + translate(user, HUNGARY), HUNGARY),
                        getButton("\ud83c\uddee\ud83c\uddf8" + translate(user, ICELAND), ICELAND)),
                Arrays.asList(getButton("\ud83c\uddee\ud83c\uddea" + translate(user, IRELAND), IRELAND),
                        getButton("\ud83c\uddee\ud83c\uddf2" + translate(user, ISLE_OF_MAN), ISLE_OF_MAN)),
                Arrays.asList(getButton("\ud83c\uddee\ud83c\uddf9" + translate(user, ITALY), ITALY),
                        getButton("\ud83c\uddef\ud83c\uddea" + translate(user, JERSEY), JERSEY)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\uddfb" + translate(user, LATVIA), LATVIA),
                        getButton("\ud83c\uddf1\ud83c\uddee" + translate(user, LIECHTENSTEIN), LIECHTENSTEIN)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\uddf9" + translate(user, LITHUANIA), LITHUANIA),
                        getButton("\ud83c\uddf1\ud83c\uddfa" + translate(user, LUXEMBOURG), LUXEMBOURG)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddf0" + translate(user, NORTH_MACEDONIA), NORTH_MACEDONIA),
                        getButton("\ud83c\uddf2\ud83c\uddf9" + translate(user, MALTA), MALTA)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\udde9" + translate(user, MOLDOVA), MOLDOVA),
                        getButton("\ud83c\uddf2\ud83c\udde8" + translate(user, MONACO), MONACO)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddea" + translate(user, MONTENEGRO), MONTENEGRO),
                        getButton("\ud83c\uddf3\ud83c\uddf1" + translate(user, NETHERLANDS), NETHERLANDS)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddf4" + translate(user, NORWAY), NORWAY),
                        getButton("\ud83c\uddf5\ud83c\uddf1" + translate(user, POLAND), POLAND)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddf9" + translate(user, PORTUGAL), PORTUGAL),
                        getButton("\ud83c\uddf7\ud83c\uddf4" + translate(user, ROMANIA), ROMANIA)),
                Arrays.asList(getButton("\ud83c\uddf7\ud83c\uddfa" + translate(user, RUSSIA), RUSSIA),
                        getButton("\ud83c\uddf8\ud83c\uddf2" + translate(user, SAN_MARINO), SAN_MARINO)),
                Arrays.asList(getButton("\ud83c\uddf7\ud83c\uddf8" + translate(user, SERBIA), SERBIA),
                        getButton("\ud83c\uddf8\ud83c\uddf0" + translate(user, SLOVAKIA), SLOVAKIA)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddee" + translate(user, SLOVENIA), SLOVENIA),
                        getButton("\ud83c\uddea\ud83c\uddf8" + translate(user, SPAIN), SPAIN)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddef" + translate(user, SVALBARD_AND_JAN_MAYEN), SVALBARD_AND_JAN_MAYEN),
                        getButton("\ud83c\uddf8\ud83c\uddea" + translate(user, SWEDEN), SWEDEN)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\udded" + translate(user, SWITZERLAND), SWITZERLAND),
                        getButton("\ud83c\uddfa\ud83c\udde6" + translate(user, UKRAINE), UKRAINE)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\udde7" + translate(user, UK), UK),
                        getButton("\ud83c\uddfb\ud83c\udde6" + translate(user, VATICAN_CITY), VATICAN_CITY)),
                Arrays.asList(getBackButton(user, START)))
        );
    }

    public InlineKeyboardMarkup getAsiaCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddf2" + translate(user, ARMENIA), ARMENIA),
                        getButton("\ud83c\udde6\ud83c\uddff" + translate(user, AZERBAIJAN), AZERBAIJAN)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\udded" + translate(user, BAHRAIN), BAHRAIN),
                        getButton("\ud83c\udde7\ud83c\udde9" + translate(user, BANGLADESH), BANGLADESH)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddf9" + translate(user, BHUTAN), BHUTAN),
                        getButton("\ud83c\uddee\ud83c\uddf4" + translate(user, BRITISH_INDIAN_OCEAN_TERRITORY), BRITISH_INDIAN_OCEAN_TERRITORY)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddf3" + translate(user, BRUNEI), BRUNEI),
                        getButton("\ud83c\uddf0\ud83c\udded" + translate(user, CAMBODIA), CAMBODIA)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddf3" + translate(user, CHINA), CHINA),
                        getButton("\ud83c\udde8\ud83c\uddfd" + translate(user, CHRISTMAS_ISLAND), CHRISTMAS_ISLAND)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\udde8" + translate(user, COCOS_KEELING_ISLANDS), COCOS_KEELING_ISLANDS),
                        getButton("\ud83c\uddec\ud83c\uddea" + translate(user, GEORGIA), GEORGIA)),
                Arrays.asList(getButton("\ud83c\udded\ud83c\uddf0" + translate(user, HONG_KONG), HONG_KONG),
                        getButton("\ud83c\uddee\ud83c\uddf3" + translate(user, INDIA), INDIA)),
                Arrays.asList(getButton("\ud83c\uddee\ud83c\udde9" + translate(user, INDONESIA), INDONESIA),
                        getButton("\ud83c\uddee\ud83c\uddf7" + translate(user, IRAN), IRAN)),
                Arrays.asList(getButton("\ud83c\uddee\ud83c\uddf6" + translate(user, IRAQ), IRAQ),
                        getButton("\ud83c\uddee\ud83c\uddf1" + translate(user, ISRAEL), ISRAEL)),
                Arrays.asList(getButton("\ud83c\uddef\ud83c\uddf5" + translate(user, JAPAN), JAPAN),
                        getButton("\ud83c\uddef\ud83c\uddf4" + translate(user, JORDAN), JORDAN)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddff" + translate(user, KAZAKHSTAN), KAZAKHSTAN),
                        getButton("\ud83c\uddf0\ud83c\uddfc" + translate(user, KUWAIT), KUWAIT)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddec" + translate(user, KYRGYZSTAN), KYRGYZSTAN),
                        getButton("\ud83c\uddf1\ud83c\udde6" + translate(user, LAOS), LAOS)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\udde7" + translate(user, LEBANON), LEBANON),
                        getButton("\ud83c\uddf2\ud83c\uddf4" + translate(user, MACAO), MACAO)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddfe" + translate(user, MALAYSIA), MALAYSIA),
                        getButton("\ud83c\uddf2\ud83c\uddfb" + translate(user, MALDIVES), MALDIVES)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddf3" + translate(user, MONGOLIA), MONGOLIA),
                        getButton("\ud83c\uddf2\ud83c\uddf2" + translate(user, MYANMAR), MYANMAR)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddf5" + translate(user, NEPAL), NEPAL),
                        getButton("\ud83c\uddf0\ud83c\uddf5" + translate(user, NORTH_KOREA), NORTH_KOREA)),
                Arrays.asList(getButton("\ud83c\uddf4\ud83c\uddf2" + translate(user, OMAN), OMAN),
                        getButton("\ud83c\uddf5\ud83c\uddf0" + translate(user, PAKISTAN), PAKISTAN)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddf8" + translate(user, PALESTINE), PALESTINE),
                        getButton("\ud83c\uddf5\ud83c\udded" + translate(user, PHILIPPINES), PHILIPPINES)),
                Arrays.asList(getButton("\ud83c\uddf6\ud83c\udde6" + translate(user, QATAR), QATAR),
                        getButton("\ud83c\uddf8\ud83c\uddec" + translate(user, SINGAPORE), SINGAPORE)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddf7" + translate(user, SOUTH_KOREA), SOUTH_KOREA),
                        getButton("\ud83c\uddf1\ud83c\uddf0" + translate(user, SRI_LANKA), SRI_LANKA)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddfe" + translate(user, SYRIA), SYRIA),
                        getButton("\ud83c\uddf9\ud83c\uddfc" + translate(user, TAIWAN), TAIWAN)),
                Arrays.asList(getButton("\ud83c\uddf9\ud83c\uddef" + translate(user, TAJIKISTAN), TAJIKISTAN),
                        getButton("\ud83c\uddf9\ud83c\udded" + translate(user, THAILAND), THAILAND)),
                Arrays.asList(getButton("\ud83c\uddf9\ud83c\uddf7" + translate(user, TURKEY), TURKEY),
                        getButton("\ud83c\uddf9\ud83c\uddf2" + translate(user, TURKMENISTAN), TURKMENISTAN)),
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddea" + translate(user, UAE), UAE),
                        getButton("\ud83c\uddfa\ud83c\uddff" + translate(user, UZBEKISTAN), UZBEKISTAN)),
                Arrays.asList(getButton("\ud83c\uddfb\ud83c\uddf3" + translate(user, VIETNAM), VIETNAM),
                        getButton("\ud83c\uddfe\ud83c\uddea" + translate(user, YEMEN), YEMEN)),
                Arrays.asList(getBackButton(user, START))));
    }

    public InlineKeyboardMarkup getNorthAmericaCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddee" + translate(user, ANGUILLA), ANGUILLA),
                        getButton("\ud83c\udde6\ud83c\uddec" + translate(user, ANTIGUA_AND_BARBUDA), ANTIGUA_AND_BARBUDA)),
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddfc" + translate(user, ARUBA), ARUBA),
                        getButton("\ud83c\udde7\ud83c\uddf8" + translate(user, BAHAMAS), BAHAMAS)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\udde7" + translate(user, BARBADOS), BARBADOS),
                        getButton("\ud83c\udde7\ud83c\uddff" + translate(user, BELIZE), BELIZE)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddf2" + translate(user, BERMUDA), BERMUDA),
                        getButton("\ud83c\udde7\ud83c\uddf6" + translate(user, BONAIRE_SINT_EUSTATIUS_AND_SABA), BONAIRE_SINT_EUSTATIUS_AND_SABA)),
                Arrays.asList(getButton("\ud83c\uddfb\ud83c\uddec" + translate(user, BRITISH_VIRGIN_ISLANDS), BRITISH_VIRGIN_ISLANDS),
                        getButton("\ud83c\udde8\ud83c\udde6" + translate(user, CANADA), CANADA)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddfe" + translate(user, CAYMAN_ISLANDS), CAYMAN_ISLANDS),
                        getButton("\ud83c\udde8\ud83c\uddf7" + translate(user, COSTA_RICA), COSTA_RICA)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddfa" + translate(user, CUBA), CUBA),
                        getButton("\ud83c\udde8\ud83c\uddfc" + translate(user, CURACAO), CURACAO)),
                Arrays.asList(getButton("\ud83c\udde9\ud83c\uddf2" + translate(user, DOMINICA), DOMINICA),
                        getButton("\ud83c\udde9\ud83c\uddf4" + translate(user, DOMINICAN_REPUBLIC), DOMINICAN_REPUBLIC)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddfb" + translate(user, EL_SALVADOR), EL_SALVADOR),
                        getButton("\ud83c\uddec\ud83c\uddf1" + translate(user, GREENLAND), GREENLAND)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\udde9" + translate(user, GRENADA), GRENADA),
                        getButton("\ud83c\uddec\ud83c\uddf5" + translate(user, GUADELOUPE), GUADELOUPE)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddf9" + translate(user, GUATEMALA), GUATEMALA),
                        getButton("\ud83c\udded\ud83c\uddf9" + translate(user, HAITI), HAITI)),
                Arrays.asList(getButton("\ud83c\udded\ud83c\uddf3" + translate(user, HONDURAS), HONDURAS),
                        getButton("\ud83c\uddef\ud83c\uddf2" + translate(user, JAMAICA), JAMAICA)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddf6" + translate(user, MARTINIQUE), MARTINIQUE),
                        getButton("\ud83c\uddf2\ud83c\uddfd" + translate(user, MEXICO), MEXICO)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddf8" + translate(user, MONTSERRAT), MONTSERRAT),
                        getButton("\ud83c\uddf3\ud83c\uddee" + translate(user, NICARAGUA), NICARAGUA)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\udde6" + translate(user, PANAMA), PANAMA),
                        getButton("\ud83c\uddf5\ud83c\uddf7" + translate(user, PUERTO_RICO), PUERTO_RICO)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddf1" + translate(user, ST_BARTH), ST_BARTH),
                        getButton("\ud83c\uddf0\ud83c\uddf3" + translate(user, SAINT_KITTS_AND_NEVIS), SAINT_KITTS_AND_NEVIS)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\udde8" + translate(user, SAINT_LUCIA), SAINT_LUCIA),
                        getButton("\ud83c\uddf2\ud83c\uddeb" + translate(user, SAINT_MARTIN), SAINT_MARTIN)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddf2" + translate(user, SAINT_PIERRE_AND_MIQUELON), SAINT_PIERRE_AND_MIQUELON),
                        getButton("\ud83c\uddfb\ud83c\udde8" + translate(user, ST_VINCENT_GRENADINES), ST_VINCENT_GRENADINES)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddfd" + translate(user, SINT_MAARTEN), SINT_MAARTEN),
                        getButton("\ud83c\uddf9\ud83c\uddf9" + translate(user, TRINIDAD_AND_TOBAGO), TRINIDAD_AND_TOBAGO)),
                Arrays.asList(getButton("\ud83c\uddf9\ud83c\udde8" + translate(user, TURKS_AND_CAICOS), TURKS_AND_CAICOS),
                        getButton("\ud83c\uddfb\ud83c\uddee" + translate(user, US_VIRGIN_ISLANDS), US_VIRGIN_ISLANDS)),
                Arrays.asList(getButton("\ud83c\uddfa\ud83c\uddf8" + translate(user, USA), USA)),
                Arrays.asList(getBackButton(user, START))
        ));
    }

    public InlineKeyboardMarkup getSouthAmericaCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddf7" + translate(user, ARGENTINA), ARGENTINA),
                        getButton("\ud83c\udde7\ud83c\uddf4" + translate(user, BOLIVIA), BOLIVIA)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddf7" + translate(user, BRAZIL), BRAZIL),
                        getButton("\ud83c\udde8\ud83c\uddf1" + translate(user, CHILE), CHILE)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddf4" + translate(user, COLOMBIA), COLOMBIA),
                        getButton("\ud83c\uddea\ud83c\udde8" + translate(user, ECUADOR), ECUADOR)),
                Arrays.asList(getButton("\ud83c\uddeb\ud83c\uddf0" + translate(user, FALKLAND_ISLANDS_MALVINAS), FALKLAND_ISLANDS_MALVINAS),
                        getButton("\ud83c\uddec\ud83c\uddeb" + translate(user, FRENCH_GUIANA), FRENCH_GUIANA)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddfe" + translate(user, GUYANA), GUYANA),
                        getButton("\ud83c\uddf5\ud83c\uddfe" + translate(user, PARAGUAY), PARAGUAY)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddea" + translate(user, PERU), PERU),
                        getButton("\ud83c\uddf8\ud83c\uddf7" + translate(user, SURINAME), SURINAME)),
                Arrays.asList(getButton("\ud83c\uddfa\ud83c\uddfe" + translate(user, URUGUAY), URUGUAY),
                        getButton("\ud83c\uddfb\ud83c\uddea" + translate(user, VENEZUELA), VENEZUELA)),
                Arrays.asList(getBackButton(user, START))

        ));
    }

    public InlineKeyboardMarkup getAfricaCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde9\ud83c\uddff" + translate(user, ALGERIA), ALGERIA),
                        getButton("\ud83c\udde6\ud83c\uddf4" + translate(user, ANGOLA), ANGOLA)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddef" + translate(user, BENIN), BENIN),
                        getButton("\ud83c\udde7\ud83c\uddfc" + translate(user, BOTSWANA), BOTSWANA)),
                Arrays.asList(getButton("\ud83c\udde7\ud83c\uddeb" + translate(user, BURKINA_FASO), BURKINA_FASO),
                        getButton("\ud83c\udde7\ud83c\uddee" + translate(user, BURUNDI), BURUNDI)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddf2" + translate(user, CAMEROON), CAMEROON),
                        getButton("\ud83c\udde8\ud83c\uddfb" + translate(user, CABO_VERDE), CABO_VERDE)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddeb" + translate(user, CAR), CAR),
                        getButton("\ud83c\uddf9\ud83c\udde9" + translate(user, CHAD), CHAD)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddf2" + translate(user, COMOROS), COMOROS),
                        getButton("\ud83c\udde8\ud83c\udde9" + translate(user, DRC), DRC)),
                Arrays.asList(getButton("\ud83c\udde9\ud83c\uddef" + translate(user, DJIBOUTI), DJIBOUTI),
                        getButton("\ud83c\uddea\ud83c\uddec" + translate(user, EGYPT), EGYPT)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddf6" + translate(user, EQUATORIAL_GUINEA), EQUATORIAL_GUINEA),
                        getButton("\ud83c\uddea\ud83c\uddf7" + translate(user, ERITREA), ERITREA)),
                Arrays.asList(getButton("\ud83c\uddea\ud83c\uddf9" + translate(user, ETHIOPIA), ETHIOPIA),
                        getButton("\ud83c\uddec\ud83c\udde6" + translate(user, GABON), GABON)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddf2" + translate(user, GAMBIA), GAMBIA),
                        getButton("\ud83c\uddec\ud83c\udded" + translate(user, GHANA), GHANA)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddf3" + translate(user, GUINEA), GUINEA),
                        getButton("\ud83c\uddec\ud83c\uddfc" + translate(user, GUINEA_BISSAU), GUINEA_BISSAU)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddee" + translate(user, IVORY_COAST), IVORY_COAST),
                        getButton("\ud83c\uddf0\ud83c\uddea" + translate(user, KENYA), KENYA)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\uddf8" + translate(user, LESOTHO), LESOTHO),
                        getButton("\ud83c\uddf1\ud83c\uddf7" + translate(user, LIBERIA), LIBERIA)),
                Arrays.asList(getButton("\ud83c\uddf1\ud83c\uddfe" + translate(user, LIBYA), LIBYA),
                        getButton("\ud83c\uddf2\ud83c\uddec" + translate(user, MADAGASCAR), MADAGASCAR)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddfc" + translate(user, MALAWI), MALAWI),
                        getButton("\ud83c\uddf2\ud83c\uddf1" + translate(user, MALI), MALI)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddf7" + translate(user, MAURITANIA), MAURITANIA),
                        getButton("\ud83c\uddf2\ud83c\uddfa" + translate(user, MAURITIUS), MAURITIUS)),
                Arrays.asList(getButton("\ud83c\uddfe\ud83c\uddf9" + translate(user, MAYOTTE), MAYOTTE),
                        getButton("\ud83c\uddf2\ud83c\udde6" + translate(user, MOROCCO), MOROCCO)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\uddff" + translate(user, MOZAMBIQUE), MOZAMBIQUE),
                        getButton("\ud83c\uddf3\ud83c\udde6" + translate(user, NAMIBIA), NAMIBIA)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddea" + translate(user, NIGER), NIGER),
                        getButton("\ud83c\uddf3\ud83c\uddec" + translate(user, NIGERIA), NIGERIA)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddec" + translate(user, CONGO), CONGO),
                        getButton("\ud83c\uddf7\ud83c\uddea" + translate(user, REUNION), REUNION)),
                Arrays.asList(getButton("\ud83c\uddf7\ud83c\uddfc" + translate(user, RWANDA), RWANDA),
                        getButton("\ud83c\uddf8\ud83c\udded" + translate(user, SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA), SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddf9" + translate(user, SAO_TOME_AND_PRINCIPE), SAO_TOME_AND_PRINCIPE),
                        getButton("\ud83c\uddf8\ud83c\uddf3" + translate(user, SENEGAL), SENEGAL)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\udde8" + translate(user, SEYCHELLES), SEYCHELLES),
                        getButton("\ud83c\uddf8\ud83c\uddf1" + translate(user, SIERRA_LEONE), SIERRA_LEONE)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddf4" + translate(user, SOMALIA), SOMALIA),
                        getButton("\ud83c\uddff\ud83c\udde6" + translate(user, SOUTH_AFRICA), SOUTH_AFRICA)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddf8" + translate(user, SOUTH_SUDAN), SOUTH_SUDAN),
                        getButton("\ud83c\uddf8\ud83c\udde9" + translate(user, SUDAN), SUDAN)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\uddff" + translate(user, ESWATINI), ESWATINI),
                        getButton("\ud83c\uddf9\ud83c\uddff" + translate(user, TANZANIA), TANZANIA)),
                Arrays.asList(getButton("\ud83c\uddf9\ud83c\uddec" + translate(user, TOGO), TOGO),
                        getButton("\ud83c\uddf9\ud83c\uddf3" + translate(user, TUNISIA), TUNISIA)),
                Arrays.asList(getButton("\ud83c\uddfa\ud83c\uddec" + translate(user, UGANDA), UGANDA),
                        getButton("\ud83c\uddea\ud83c\udded" + translate(user, WESTERN_SAHARA), WESTERN_SAHARA)),
                Arrays.asList(getButton("\ud83c\uddff\ud83c\uddf2" + translate(user, ZAMBIA), ZAMBIA),
                        getButton("\ud83c\uddff\ud83c\uddfc" + translate(user, ZIMBABWE), ZIMBABWE)),
                Arrays.asList(getBackButton(user, START))
        ));
    }

    public InlineKeyboardMarkup getAustraliaAndOceaniaCountriesKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\ud83c\udde6\ud83c\uddf8" + translate(user, AMERICAN_SAMOA), AMERICAN_SAMOA),
                        getButton("\ud83c\udde6\ud83c\uddfa" + translate(user, AUSTRALIA), AUSTRALIA)),
                Arrays.asList(getButton("\ud83c\udde8\ud83c\uddf0" + translate(user, COOK_ISLANDS), COOK_ISLANDS),
                        getButton("\ud83c\uddf9\ud83c\uddf1" + translate(user, TIMOR_LESTE), TIMOR_LESTE)),
                Arrays.asList(getButton("\ud83c\uddeb\ud83c\uddef" + translate(user, FIJI), FIJI),
                        getButton("\ud83c\uddf5\ud83c\uddeb" + translate(user, FRENCH_POLYNESIA), FRENCH_POLYNESIA)),
                Arrays.asList(getButton("\ud83c\uddec\ud83c\uddfa" + translate(user, GUAM), GUAM),
                        getButton("\ud83c\uddf0\ud83c\uddee" + translate(user, KIRIBATI), KIRIBATI)),
                Arrays.asList(getButton("\ud83c\uddf2\ud83c\udded" + translate(user, MARSHALL_ISLANDS), MARSHALL_ISLANDS),
                        getButton("\ud83c\uddeb\ud83c\uddf2" + translate(user, MICRONESIA), MICRONESIA)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddf7" + translate(user, NAURU), NAURU),
                        getButton("\ud83c\uddf3\ud83c\udde8" + translate(user, NEW_CALEDONIA), NEW_CALEDONIA)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddff" + translate(user, NEW_ZEALAND), NEW_ZEALAND),
                        getButton("\ud83c\uddf3\ud83c\uddfa" + translate(user, NIUE), NIUE)),
                Arrays.asList(getButton("\ud83c\uddf3\ud83c\uddeb" + translate(user, NORFOLK_ISLAND), NORFOLK_ISLAND),
                        getButton("\ud83c\uddf2\ud83c\uddf5" + translate(user, NORTHERN_MARIANA_ISLANDS), NORTHERN_MARIANA_ISLANDS)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddfc" + translate(user, PALAU), PALAU),
                        getButton("\ud83c\uddf5\ud83c\uddec" + translate(user, PAPUA_NEW_GUINEA), PAPUA_NEW_GUINEA)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddf3" + translate(user, PITCAIRN), PITCAIRN),
                        getButton("\ud83c\uddfc\ud83c\uddf8" + translate(user, SAMOA), SAMOA)),
                Arrays.asList(getButton("\ud83c\uddf8\ud83c\udde7" + translate(user, SOLOMON_ISLANDS), SOLOMON_ISLANDS),
                        getButton("\ud83c\uddf9\ud83c\uddf0" + translate(user, TOKELAU), TOKELAU)),
                Arrays.asList(getButton("\ud83c\uddf9\ud83c\uddf4" + translate(user, TONGA), TONGA),
                        getButton("\ud83c\uddf9\ud83c\uddfb" + translate(user, TUVALU), TUVALU)),
                Arrays.asList(getButton("\ud83c\uddfa\ud83c\uddf2" + translate(user, UNITED_STATES_MINOR_OUTLYING_ISLANDS), UNITED_STATES_MINOR_OUTLYING_ISLANDS),
                        getButton("\ud83c\uddfb\ud83c\uddfa" + translate(user, VANUATU), VANUATU)),
                Arrays.asList(getButton("\ud83c\uddfc\ud83c\uddeb" + translate(user, WALLIS_AND_FUTUNA), WALLIS_AND_FUTUNA)),

                Arrays.asList(getBackButton(user, START))
        ));
    }

    private String translate(User user, String actionName) {
        return getRb(user).getString(toResource(actionName));
    }

    public InlineKeyboardMarkup getStartKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\uD83D\uDC69\uD83C\uDFFB\u200D⚕ " + getRb(user).getString("who_info"), WHO_INFORMATION)),
                Arrays.asList(getButton("\uD83C\uDDFA\uD83C\uDDE6 " + getRb(user).getString("ukraine"), UKRAINE),
                        getButton("\uD83D\uDD14 " + getRb(user).getString("notifications"), NOTIFICATIONS)),
                Arrays.asList(getButton("\uD83C\uDF0D " + getRb(user).getString("world"), WORLD),
                        getButton("\uD83D\uDCF0 " + getRb(user).getString("news"), NEWS),
                        getButton("\uD83E\uDDED " + getRb(user).getString("countries"), COUNTRIES)),
                Arrays.asList(getButton("\uD83C\uDF10 " + getRb(user).getString("lang"), LANGUAGE),
                        getButton("\uD83D\uDE37 " + getRb(user).getString("quarantine"), QUARANTINE),
                        getButton("ℹ " + getRb(user).getString("about"), ABOUT))));
    }

    public InlineKeyboardMarkup getLanguageKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\uD83C\uDDFA\uD83C\uDDE6 Українська", UKRAINIAN),
                        getButton("\uD83C\uDDF7\uD83C\uDDFA Русский", RUSSIAN),
                        getButton("\uD83C\uDDEC\uD83C\uDDE7 English", ENGLISH)),
                Arrays.asList(getBackButton(user, START))));
    }

    public InlineKeyboardMarkup getContinentsKeyboard(User user) {
        return new InlineKeyboardMarkup().setKeyboard(Arrays.asList(
                Arrays.asList(getButton("\uD83C\uDDEA\uD83C\uDDFA " + getRb(user).getString("europe"), EUROPE),
                        getButton("\uD83C\uDDE8\uD83C\uDDF3 " + getRb(user).getString("asia"), ASIA)),
                Arrays.asList(getButton("\uD83C\uDDFA\uD83C\uDDF8 " + getRb(user).getString("north_america"), NORTH_AMERICA),
                        getButton("\uD83C\uDDE7\uD83C\uDDF7 " + getRb(user).getString("south_america"), SOUTH_AMERICA)),
                Arrays.asList(getButton("\uD83C\uDDFF\uD83C\uDDE6 " + getRb(user).getString("africa"), AFRICA),
                        getButton("\uD83C\uDDE6\uD83C\uDDFA " + getRb(user).getString("australia_and_oceania"), AUSTRALIA_AND_OCEANIA)),
                Arrays.asList(getBackButton(user, START))));
    }

    public ResourceBundle getRb(User user) {
        return ResourceBundle.getBundle("messages", new Locale(telegramUserService.initUser(user).getLocale()));
    }
}
