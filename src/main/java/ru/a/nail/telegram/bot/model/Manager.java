package ru.a.nail.telegram.bot.model;

import lombok.Setter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Getter
@Setter
@Component
public abstract class Manager {

    private final TelegramBot bot;

    protected Manager(TelegramBot bot) {
        this.bot = bot;
    }

    public abstract BotApiMethod<?> answerCommand(Message message);

    public abstract BotApiMethod<?> answerQuery(CallbackQuery query);
}
