package com.example.coronastats.telegram.service;

import com.example.coronastats.telegram.model.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramUserService {

    TelegramUser getUser(Integer telegramId);

    TelegramUser initUser(User user);

    TelegramUser changeLocale(Integer telegramId, String locale) throws Exception;

    long getUsersCount();
}
