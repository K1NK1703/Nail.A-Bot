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

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.price;
import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.advice;
import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.recording;
import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.gallery;
import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.contact;

@Service("menu")
public class MenuManager extends Manager {

    protected MenuManager(TelegramBot bot) {
        super(bot);
    }

    @Override
    public BotApiMethod<?> answerCommand(Message message) {
        return SendMessage.builder()
                .text(
                        """
                        Ты в главном меню! 👩‍💻
                        
                        Выбери любую из команд:
                        
                        1. Записаться - выбери удобное время и дату для записи на маникюр 🤝
                        2. Галерея - здесь можно посмотреть работы мастера 📸
                        3. Прайс - самые приятные и актуальные цены и специальные предложения 🫰
                        4. Рекомендации - небольшие советы по уходу за ноготочками 😊
                        5. Контакты - узнать о координатах и местоположении можно в этом разделе 🔎
                        
                        Альтернативное меню - /help 🙏
                        """
                )
                .chatId(message.getChatId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Записаться", "Галерея", "Прайс", "Рекомендации", "Контакты"),
                                List.of(1, 2, 2),
                                List.of(recording.name(), gallery.name(), price.name(), advice.name(), contact.name())
                        )
                )
                .build();
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query) {
        return EditMessageText.builder()
                .text(
                        """
                        Ты в главном меню! 👩‍💻
                        
                        Выбери любую из команд:
                        
                        1. Записаться - выбери удобное время и дату для записи на маникюр 🤝
                        2. Галерея - здесь можно посмотреть работы мастера 📸
                        3. Прайс - самые приятные и актуальные цены и специальные предложения 🫰
                        4. Рекомендации - небольшие советы по уходу за ноготочками 😊
                        5. Контакты - узнать о координатах и местоположении можно в этом разделе 🔎
                        
                        Альтернативное меню - /help 🙏
                        """
                )
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Записаться", "Галерея", "Прайс", "Рекомендации", "Контакты"),
                                List.of(1, 2, 2),
                                List.of(recording.name(), gallery.name(), price.name(), advice.name(), contact.name())
                        )
                )
                .build();
    }
}
