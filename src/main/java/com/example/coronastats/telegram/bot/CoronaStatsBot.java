package com.example.coronastats.telegram.bot;


import com.example.coronastats.common.util.LogUtil;
import com.example.coronastats.stats.service.StatsService;
import com.example.coronastats.stats.service.StatsUtil;
import com.example.coronastats.telegram.model.TelegramUser;
import com.example.coronastats.telegram.service.TelegramInterfaceHelper;
import com.example.coronastats.telegram.service.TelegramUserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.coronastats.telegram.bot.Actions.*;

@Slf4j
@Component
public final class CoronaStatsBot extends TelegramLongPollingBot {

    @Autowired
    private TelegramUserService telegramUserService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private TelegramInterfaceHelper telegramInterfaceHelper;
    
    @Value("${telegram.botUserName}")
    private String botUserName;

    @Value("${telegram.token}")
    private String botToken;

    public CoronaStatsBot() {
        super();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            processCommand(update);
        } else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        }
    }

    private void processCommand(Update update) throws TelegramApiException {
        String command = update.getMessage().getText();
        if (START.equals(command)) {
            startCommandCallback(update);
        }
    }

    private void startCommandCallback(Update update) throws TelegramApiException {
        User user = update.getMessage().getFrom();
        LogUtil.logAction(logger, user, update.getMessage().getText());
        TelegramUser telegramUser = telegramUserService.initUser(update.getMessage().getFrom());
        execute(new SendMessage().setChatId(update.getMessage().getChatId())
                .setText(telegramInterfaceHelper.getGreetingText(user))
                .setReplyMarkup(telegramInterfaceHelper.getStartKeyboard(user)));
    }

    private void processCallbackQuery(Update update) throws Exception {
        String query = update.getCallbackQuery().getData();
        if (START.equals(query)) {
            startButtonCallback(update);
        } else if (WORLD.equals(query)) {
            worldButtonCallback(update);
        } else if (COUNTRIES.equals(query)) {
            countriesButtonCallback(update);
        } else if (EUROPE.equals(query)) {
            europeButtonCallback(update);
        } else if (ASIA.equals(query)) {
            asiaButtonCallback(update);
        } else if (NORTH_AMERICA.equals(query)) {
            northAmericaButtonCallback(update);
        } else if (SOUTH_AMERICA.equals(query)) {
            southAmericaButtonCallback(update);
        } else if (AFRICA.equals(query)) {
            africaButtonCallback(update);
        } else if (AUSTRALIA_AND_OCEANIA.equals(query)) {
            australiaAndOceaniaButtonCallback(update);
        } else if (StatsUtil.isCountry(query)) {
            countryButtonCallback(update);
        } else if (LANGUAGE.equals(query)) {
            languageButtonCallback(update);
        } else if (Arrays.asList(UKRAINIAN, RUSSIAN, ENGLISH).contains(query)) {
            telegramUserService.changeLocale(update.getCallbackQuery().getFrom().getId(), query.substring(1));
            startButtonCallback(update);
        } else if (ABOUT.equals(query)) {
            aboutButtonCallback(update);
        }
    }

    private void startButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        TelegramUser telegramUser = telegramUserService.initUser(update.getCallbackQuery().getFrom());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getGreetingText(user))
                .setReplyMarkup(telegramInterfaceHelper.getStartKeyboard(user)));
    }

    private void worldButtonCallback(Update update) throws TelegramApiException, UnirestException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        String messageText = StatsUtil.formatTotalStats(getRb(user), statsService.getDetailedTotalStats());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(messageText)
                .setReplyMarkup(telegramInterfaceHelper.getBackKeyboard(user, START)));
    }

    private void countriesButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getContinentsKeyboard(user)));
    }

    private void europeButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getEuropeCountriesKeyboard(user)));

    }

    private void asiaButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getAsiaCountriesKeyboard(user)));

    }

    private void northAmericaButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getNorthAmericaCountriesKeyboard(user)));

    }

    private void southAmericaButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getSouthAmericaCountriesKeyboard(user)));

    }

    private void africaButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getAfricaCountriesKeyboard(user)));

    }

    private void australiaAndOceaniaButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getAustraliaAndOceaniaCountriesKeyboard(user)));

    }

    private void countryButtonCallback(Update update) throws TelegramApiException, UnirestException {
        User user = update.getCallbackQuery().getFrom();
        String action = update.getCallbackQuery().getData();
        LogUtil.logAction(logger, user, action);
        String messageText = StatsUtil.formatCountryStats(getRb(user), statsService
                .getDetailedCountryStats(StatsUtil.getCountryName(action)));
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(messageText)
                .setReplyMarkup(telegramInterfaceHelper.getBackKeyboard(user, StatsUtil.getContinent(action))));
    }

    private void languageButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(getRb(user).getString("lang.choose"))
                .setReplyMarkup(telegramInterfaceHelper.getLanguageKeyboard(user)));
    }


    private void aboutButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        LogUtil.logAction(logger, user, update.getCallbackQuery().getData());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(getRb(user).getString("about_text") + "\n" + "\uD83D\uDE0A")
                .setReplyMarkup(telegramInterfaceHelper.getBackKeyboard(user, START)));
    }

    private ResourceBundle getRb(User user) {
        return ResourceBundle.getBundle("messages", new Locale(telegramUserService.initUser(user).getLocale()));
    }
    
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}