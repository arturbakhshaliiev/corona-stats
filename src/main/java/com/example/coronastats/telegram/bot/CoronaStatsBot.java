package com.example.coronastats.telegram.bot;


import com.example.coronastats.stats.service.StatsService;
import com.example.coronastats.stats.service.StatsUtil;
import com.example.coronastats.telegram.bot.dto.Action;
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
import java.util.Optional;
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
        logger.info("Telegram Bot command success. User {}. Command {}", user, update.getMessage());
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
        } else if (Arrays.asList(ALAND_ISLANDS, ALBANIA, ANDORRA, AUSTRIA, BELARUS, BELGIUM, BOSNIA_AND_HERZEGOVINA, BULGARIA,
                CHANNEL_ISLANDS, CROATIA, CYPRUS, CZECHIA, ESTONIA, FAEROE_ISLANDS, FALKLAND_ISLANDS, FINLAND, FRANCE,
                GERMANY, GIBRALTAR, GREECE, GREENLAND, GUERNSEY, HUNGARY, ICELAND, IRELAND, ISLE_OF_MAN, ITALY, JERSEY,
                LATVIA, LIECHTENSTEIN, LITHUANIA, LUXEMBOURG, MALTA, MOLDOVA, MONACO, MONTENEGRO, NETHERLANDS,
                NORTH_MACEDONIA, NORWAY, POLAND, PORTUGAL, RUSSIA, SAN_MARINO, SERBIA, SLOVAKIA, SLOVENIA, SPAIN,
                SVALBARD, SWEDEN, SWITZERLAND, UK, UKRAINE, VATICAN).contains(query)) {
            countryButtonCallback(update);
        } else if (LANGUAGE.equals(query)) {
            languageButtonCallback(update);
        } else if (Arrays.asList(UKRAINIAN, RUSSIAN, ENGLISH).contains(query)) {
            telegramUserService.changeLocale(update.getCallbackQuery().getFrom().getId(), query.substring(1));
            startButtonCallback(update);
        }
    }

    private void startButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        logger.info("Telegram Bot command success. User {}. Command {}", user, update.getCallbackQuery().getData());
        TelegramUser telegramUser = telegramUserService.initUser(update.getCallbackQuery().getFrom());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getGreetingText(user))
                .setReplyMarkup(telegramInterfaceHelper.getStartKeyboard(user)));
    }

    private void worldButtonCallback(Update update) throws TelegramApiException, UnirestException {
        User user = update.getCallbackQuery().getFrom();
        logger.info("Telegram Bot command success. User {}. Command {}", user,
                Optional.ofNullable(Action.builder().build(update)).map(Action::getName).orElse(""));
        String messageText = StatsUtil.formatTotalStats(getRb(user), statsService.getDetailedTotalStats());
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(messageText)
                .setReplyMarkup(telegramInterfaceHelper.getBackKeyboard(user, START)));
    }

    private void countriesButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        logger.info("Telegram Bot command success. User {}. Command {}",
                update.getCallbackQuery().getFrom().getUserName(),
                Optional.ofNullable(Action.builder().build(update)).map(Action::getName).orElse(""));
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getContinentsKeyboard(user)));
    }

    private void europeButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        logger.info("Telegram Bot command success. User {}. Command {}", user,
                Optional.ofNullable(Action.builder().build(update)).map(Action::getName).orElse(""));
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(telegramInterfaceHelper.getRb(user).getString("countries"))
                .setReplyMarkup(telegramInterfaceHelper.getEuropeCountriesKeyboard(user)));

    }

    private void countryButtonCallback(Update update) throws TelegramApiException, UnirestException {
        User user = update.getCallbackQuery().getFrom();
        String action = Optional.ofNullable(Action.builder().build(update)).map(Action::getName).orElse("");
        logger.info("Telegram Bot command success. User {}. Command {}", user, action);
        String messageText = StatsUtil.formatCountryStats(getRb(user), statsService
                .getDetailedCountryStats(StatsUtil.getCountryName(action)));
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(messageText)
                .setReplyMarkup(telegramInterfaceHelper.getBackKeyboard(user, EUROPE)));
    }

    private void languageButtonCallback(Update update) throws TelegramApiException {
        User user = update.getCallbackQuery().getFrom();
        logger.info("Telegram Bot command success. User {}. Command {}", user,
                Optional.ofNullable(Action.builder().build(update)).map(Action::getName).orElse(""));
        execute(new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(getRb(user).getString("lang.choose"))
                .setReplyMarkup(telegramInterfaceHelper.getLanguageKeyboard(user)));
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