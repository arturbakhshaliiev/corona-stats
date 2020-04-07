package com.example.coronastats.telegram.service;

import com.example.coronastats.telegram.model.TelegramUser;
import com.example.coronastats.telegram.repository.TelegramUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Service
public class DefaultTelegramUserService implements TelegramUserService {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Override
    public TelegramUser getUser(Integer telegramId) {
        return telegramUserRepository.findByTelegramId(telegramId);
    }

    @Transactional
    @Override
    public TelegramUser initUser(User user) {
        TelegramUser dbUser = telegramUserRepository.findByTelegramId(user.getId());
        if (dbUser != null) {
            if (user.getUserName() != null && !user.getUserName().equals(dbUser.getUsername())) {
                dbUser.setUsername(user.getUserName());
            }
            String fullNme = user.getFirstName() + " " + user.getLastName();
            if (!fullNme.equals(dbUser.getFullName())) {
                dbUser.setFullName(fullNme);
            }
            return dbUser;
        }
        return telegramUserRepository.save(TelegramUser.builder()
                .telegramId(user.getId())
                .username(user.getUserName())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .locale(user.getLanguageCode())
                .build());
    }

    @Transactional
    @Override
    public TelegramUser changeLocale(Integer telegramId, String locale) throws Exception {
        TelegramUser user = telegramUserRepository.findByTelegramId(telegramId);
        if (user == null) {
            throw new IllegalArgumentException("User is not in database.");
        }
        user.setLocale(locale);
        return telegramUserRepository.save(user);
    }

    @Override
    public long getUsersCount() {
        return telegramUserRepository.count();
    }
}
