package com.example.coronastats.telegram.service;

import com.example.coronastats.telegram.bot.dto.Action;
import com.example.coronastats.telegram.model.TelegramUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;
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
                Arrays.asList(getButton("\uD83C\uDDE6\uD83C\uDDFD Аландські острови", ALAND_ISLANDS),
                        getButton("\uD83C\uDDE6\uD83C\uDDF1 Албанія", ALBANIA)),
                Arrays.asList(getButton("\uD83C\uDDE6\uD83C\uDDE9 Андорра", ANDORRA),
                        getButton("\uD83C\uDDE6\uD83C\uDDF9 Австрія", AUSTRIA)),
                Arrays.asList(getButton("\uD83C\uDDE7\uD83C\uDDFE Білорусь", BELARUS),
                        getButton("\uD83C\uDDE7\uD83C\uDDEA Бельгія", BELGIUM)),
                Arrays.asList(getButton("\uD83C\uDDE7\uD83C\uDDE6 Боснія і Герцеговина", BOSNIA_AND_HERZEGOVINA),
                        getButton("\uD83C\uDDE7\uD83C\uDDEC Болгарія", BULGARIA)),
                Arrays.asList(getButton("\uD83C\uDDF0\uD83C\uDDFE Нормандські острови", CHANNEL_ISLANDS),
                        getButton("\uD83C\uDDE8\uD83C\uDDFE Кіпр", CYPRUS)),
                Arrays.asList(getButton("\uD83C\uDDE8\uD83C\uDDFF Чехія", CZECHIA),
                        getButton("\uD83C\uDDE8\uD83C\uDDFF Естонія", ESTONIA)),
                Arrays.asList(getButton("\uD83C\uDDEB\uD83C\uDDF4 Фарерські острови", FAEROE_ISLANDS),
                        getButton("\uD83C\uDDEB\uD83C\uDDF0 Фолклендські острови", FALKLAND_ISLANDS)),
                Arrays.asList(getButton("\uD83C\uDDEB\uD83C\uDDEE Фінляндія", FINLAND),
                        getButton("\uD83C\uDDEB\uD83C\uDDF7 Франція", FRANCE)),
                Arrays.asList(getButton("\uD83C\uDDE9\uD83C\uDDEA Німеччина", GERMANY),
                        getButton("\uD83C\uDDEC\uD83C\uDDEE Гібралтар", GIBRALTAR)),
                Arrays.asList(getButton("\uD83C\uDDEC\uD83C\uDDF7 Греція", GREECE),
                        getButton("\uD83C\uDDEC\uD83C\uDDF1 Гренландія", GREENLAND)),
                Arrays.asList(getButton("\uD83C\uDDEC\uD83C\uDDEC Гуернсі", GUERNSEY),
                        getButton("\uD83C\uDDED\uD83C\uDDFA Угорщина", HUNGARY)),
                Arrays.asList(getButton("\uD83C\uDDEE\uD83C\uDDF8 Ісландія", ICELAND),
                        getButton("\uD83C\uDDEE\uD83C\uDDEA Ірландія", IRELAND)),
                Arrays.asList(getButton("\uD83C\uDDEE\uD83C\uDDF2 Острів Мен", ISLE_OF_MAN),
                        getButton("\uD83C\uDDEE\uD83C\uDDF9 Італія", ITALY)),
                Arrays.asList(getButton("\uD83C\uDDEF\uD83C\uDDEA Джерсі", JERSEY),
                        getButton("\uD83C\uDDF1\uD83C\uDDFB Латвія", LATVIA)),
                Arrays.asList(getButton("\uD83C\uDDF1\uD83C\uDDEE Ліхтенштейн", LIECHTENSTEIN),
                        getButton("\uD83C\uDDF1\uD83C\uDDF9 Литва", LITHUANIA)),
                Arrays.asList(getButton("\uD83C\uDDF1\uD83C\uDDFA Люксембург", LUXEMBOURG),
                        getButton("\uD83C\uDDF2\uD83C\uDDF9 Мальта", MALTA)),
                Arrays.asList(getButton("\uD83C\uDDF2\uD83C\uDDE9 Молдова", MOLDOVA),
                        getButton("\uD83C\uDDF2\uD83C\uDDE8 Монако", MONACO)),
                Arrays.asList(getButton("\uD83C\uDDF2\uD83C\uDDEA Чорногорія", MONTENEGRO),
                        getButton("\uD83C\uDDF3\uD83C\uDDF1 Нідерланди", NETHERLANDS)),
                Arrays.asList(getButton("\uD83C\uDDF2\uD83C\uDDF0 Північна Мекдонія", NORTH_MACEDONIA),
                        getButton("\uD83C\uDDF3\uD83C\uDDF4 Норвегія", NORWAY)),
                Arrays.asList(getButton("\uD83C\uDDF5\uD83C\uDDF1 Польща", POLAND),
                        getButton("\uD83C\uDDF5\uD83C\uDDF9 Португалія", PORTUGAL)),
                Arrays.asList(getButton("\uD83C\uDDF7\uD83C\uDDFA Росія", RUSSIA),
                        getButton("\uD83C\uDDF8\uD83C\uDDF2 Сан Марино", SAN_MARINO)),
                Arrays.asList(getButton("\uD83C\uDDF7\uD83C\uDDF8 Сербія", SERBIA),
                        getButton("\uD83C\uDDF8\uD83C\uDDF0 Словакія", SLOVAKIA)),
                Arrays.asList(getButton("\uD83C\uDDF8\uD83C\uDDEE Словенія", SLOVENIA),
                        getButton("\uD83C\uDDEA\uD83C\uDDF8 Іспанія", SPAIN)),
                Arrays.asList(getButton("\uD83C\uDDF8\uD83C\uDDEF Шпіцберген", SVALBARD),
                        getButton("\uD83C\uDDF8\uD83C\uDDEA Швеція", SWEDEN)),
                Arrays.asList(getButton("\uD83C\uDDE8\uD83C\uDDED Швейцарія", SWITZERLAND),
                        getButton("\uD83C\uDDEC\uD83C\uDDE7 Британія", UK)),
                Arrays.asList(getButton("\uD83C\uDDFA\uD83C\uDDE6 Україна", UKRAINE),
                        getButton("\uD83C\uDDFB\uD83C\uDDE6 Ватикан", VATICAN)),
                Arrays.asList(getButton("\uD83C\uDDED\uD83C\uDDF7 Хорватія", CROATIA)),
                Arrays.asList(getBackButton(user, START))));
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
                        getButton("\uD83C\uDDE6\uD83C\uDDFA " + getRb(user).getString("australia"), AUSTRALIA_AND_OCEANIA)),
                Arrays.asList(getBackButton(user, START))));
    }

    public ResourceBundle getRb(User user) {
        return ResourceBundle.getBundle("messages", new Locale(telegramUserService.initUser(user).getLocale()));
    }
}
