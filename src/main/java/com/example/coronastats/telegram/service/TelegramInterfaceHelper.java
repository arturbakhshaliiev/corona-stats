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
                        getButton("\ud83c\uddf0\ud83c\uddf5" + translate(user, N_KOREA), N_KOREA)),
                Arrays.asList(getButton("\ud83c\uddf4\ud83c\uddf2" + translate(user, OMAN), OMAN),
                        getButton("\ud83c\uddf5\ud83c\uddf0" + translate(user, PAKISTAN), PAKISTAN)),
                Arrays.asList(getButton("\ud83c\uddf5\ud83c\uddf8" + translate(user, PALESTINE), PALESTINE),
                        getButton("\ud83c\uddf5\ud83c\udded" + translate(user, PHILIPPINES), PHILIPPINES)),
                Arrays.asList(getButton("\ud83c\uddf6\ud83c\udde6" + translate(user, QATAR), QATAR),
                        getButton("\ud83c\uddf8\ud83c\uddec" + translate(user, SINGAPORE), SINGAPORE)),
                Arrays.asList(getButton("\ud83c\uddf0\ud83c\uddf7" + translate(user, S_KOREA), S_KOREA),
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
