package com.example.coronastats.telegram.repository;

import com.example.coronastats.telegram.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {

    TelegramUser findByTelegramId(Integer telegramId);
}
