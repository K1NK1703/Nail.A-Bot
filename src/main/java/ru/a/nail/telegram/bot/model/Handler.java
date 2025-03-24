package ru.a.nail.telegram.bot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

@Getter
@Setter
@Component
public abstract class Handler<T extends BotApiObject> {

    private final Map<String, Manager> managers;

    protected Handler(Map<String, Manager> managers) {
        this.managers = managers;
    }

    protected abstract BotApiMethod<?> answer(T handle);

    protected BotApiMethod<?> unknownHandle(Long chatId, String handle) {
        return SendMessage.builder()
                .text("❌ Я не знаю команды {" + handle + "} ❌\n\n↘️ Ознакомьтесь с перечнем команд по команде ниже ↙️\n\nПеречень команд - /help")
                .chatId(chatId)
                .build();
    }

    protected BotApiMethod<?> showListOfCommands(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(
                        """
                        📌 /start - начать
                        📌 /menu - главное меню
                        📌 /gallery - галерея работ
                        📌 /price - прайс-лист и акции
                        📌 /recording - запись к мастеру
                        📌 /contact - контактная информация
                        📌 /advice - рекомендации по уходу за ногтями
                        📌 /help - перечень команд
                        """
                )
                .build();
    }
}
