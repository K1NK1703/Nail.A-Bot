package ru.a.nail.telegram.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.a.nail.telegram.bot.model.Handler;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.Manager;

import java.util.List;
import java.util.Map;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.menu;

@Service
public class MessageHandler extends Handler<Message> {

    protected MessageHandler(Map<String, Manager> managers) {
        super(managers);
    }

    @Override
    public BotApiMethod<?> answer(Message message) {
        return SendMessage.builder()
                .text("❌ Я не умею общаться текстом, взаимодействуй со мной через кнопки в меню\n\n⬇️⬇️️⬇️")
                .chatId(message.getChatId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("На главную"),
                                List.of(1),
                                List.of(menu.name())
                        )
                )
                .build();
    }
}
