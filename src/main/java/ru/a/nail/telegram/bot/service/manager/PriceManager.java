package ru.a.nail.telegram.bot.service.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.model.TelegramBot;
import ru.a.nail.telegram.bot.utils.wrapper.SendPhotoWrapper;

import java.util.List;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.back;

@Slf4j
@Service("price")
public class PriceManager extends Manager {

    private static final InputFile PHOTO = new InputFile("AgACAgIAAxkBAAIBTGfKEn8e-Eubt1Mdfx0FrjLArxphAAJh7TEbNXxRSkdDQtlNBCkuAQADAgADeQADNgQ");

    protected PriceManager(TelegramBot bot) {
        super(bot);
    }

    @Override
    public BotApiMethod<?> answerCommand(Message message) {
        return sendPhoto(message.getChatId());
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query) {
        return sendPhoto(query.getMessage().getChatId());
    }

    private SendPhotoWrapper sendPhoto(Long chatId) {
        SendPhoto sendPhoto = SendPhoto.builder()
                .caption(
                        """
                        📃 Актуальный прайс-лист на маникюр ✨
                        
                        🔥 АКЦИЯ 🔥
                        «Приведи подругу — получи скидку!»
                        
                        Порекомендуйте мои услуги своей подруге и получите 10% скидки каждая! 🎉
                        
                        Как это работает?
                        1️⃣ Запишите свою подругу на маникюр, или просто предупредите её, чтобы она указала ваше имя при записи через меня.
                        2️⃣ И вы, и ваша подруга получите 10% скидки на услуги!
                        
                        💖 Дарите друг другу красоту и выгодные бонусы! Не упустите шанс сэкономить, создавая идеальные ногти! 💅✨
                        """
                )
                .photo(PHOTO)
                .chatId(chatId)
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Назад ⬅️"),
                                List.of(1),
                                List.of(back.name())
                        )
                )
                .build();

        try {
            super.getBot().execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Error sending photo", e);
        }

        return new SendPhotoWrapper(sendPhoto);
    }
}
