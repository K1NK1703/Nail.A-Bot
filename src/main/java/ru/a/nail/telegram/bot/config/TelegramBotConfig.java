package ru.a.nail.telegram.bot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramBotConfig {
    String botUsername;
    String botToken;
    String webhookPath;
}
