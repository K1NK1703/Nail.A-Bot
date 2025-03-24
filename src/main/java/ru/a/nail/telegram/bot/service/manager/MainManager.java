package ru.a.nail.telegram.bot.service.manager;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.model.TelegramBot;

import java.util.List;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.menu;
import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.recording;

@Service("start")
public class MainManager extends Manager {

    protected MainManager(TelegramBot bot) {
        super(bot);
    }

    @Override
    public BotApiMethod<?> answerCommand(Message message) {
        return SendMessage.builder()
                .text(
                        """
                        ✨ Привет, красотка! ✨
                        
                        Добро пожаловать в мир идеального маникюра! 💅
                        
                        💖 Красивые ногти — твоя визитная карточка! Доверься профессионалу, и я сделаю твои ручки безупречными.
                        
                        Готова к преображению? Нажимай «Записаться» и создадим волшебство вместе! ✨
                        """
                )
                .chatId(message.getChatId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Записаться", "Главное меню"),
                                List.of(1, 1),
                                List.of(recording.name(), menu.name())
                        )
                )
                .build();
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query) {
        return EditMessageText.builder()
                .text(
                        """
                        ✨ Привет, красотка! ✨
                        
                        Добро пожаловать в мир идеального маникюра! 💅
                        
                        💖 Красивые ногти — твоя визитная карточка! Доверься профессионалу, и я сделаю твои ручки безупречными.
                        
                        Готова к преображению? Нажимай «Записаться» и создадим волшебство вместе! ✨
                        """
                )
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Записаться", "Главное меню"),
                                List.of(1, 1),
                                List.of(recording.name(), menu.name())
                        )
                )
                .build();
    }
}
