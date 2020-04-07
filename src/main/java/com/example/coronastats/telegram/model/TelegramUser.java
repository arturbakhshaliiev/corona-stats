package com.example.coronastats.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "corona_telegram_user")
@TableGenerator(name = "telegram_sequence", table = "hibernate_user_sequences", pkColumnValue = "telegram_user",
        initialValue = 1, allocationSize = 1)
@Data @AllArgsConstructor @NoArgsConstructor @Builder(toBuilder = true)

public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "telegram_sequence")
    private Long id;

    @Column(name = "telegram_id", unique = true)
    private Integer telegramId;

    private String username;

    private String fullName;

    private String locale;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }
}
