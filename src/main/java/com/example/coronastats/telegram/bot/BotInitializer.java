package com.example.coronastats.telegram.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class BotInitializer {

    @Value("${telegram.botUserName}")
    private String botUserName;

    @Value("${telegram.token}")
    private String botToken;

    @Autowired
    private CoronaStatsBot coronaStatsBot;

    static {
        ApiContextInitializer.init();
    }

    @PostConstruct
    private void setup() {
        try {
            logger.info("Initializing API context...");

            TelegramBotsApi botsApi = new TelegramBotsApi();

            logger.info("Registering Corona Stats bot...");
            botsApi.registerBot(coronaStatsBot);

            logger.info("Corona Stats bot is ready for work!");

        } catch (TelegramApiRequestException e) {
            logger.error("Error while initializing bot!", e);
        }
    }
}