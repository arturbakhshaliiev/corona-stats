package com.example.coronastats.common.util;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;
import org.slf4j.Logger;

public class LogUtil {

    public static void logAction(Logger logger, User user, String action) {
        String userIdentifier = Optional.ofNullable(user.getUserName())
                .orElse(user.getId() + "(" + user.getFirstName() +
                        Optional.ofNullable(user.getLastName()).map(" "::concat).orElse("") + ")");
        logger.info(String.format("Telegram Bot command success. User %s. Action %s.", userIdentifier, action));
    }
}
