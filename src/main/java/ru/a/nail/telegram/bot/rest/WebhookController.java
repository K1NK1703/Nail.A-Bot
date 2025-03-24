package ru.a.nail.telegram.bot.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.a.nail.telegram.bot.model.TelegramBot;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final TelegramBot bot;

    @PostMapping("/")
    public BotApiMethod<?> updateListener(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
