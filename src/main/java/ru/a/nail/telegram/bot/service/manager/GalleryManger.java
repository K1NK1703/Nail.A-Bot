package ru.a.nail.telegram.bot.service.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.model.TelegramBot;

import java.util.ArrayList;
import java.util.List;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.back;

@Slf4j
@Service("gallery")
public class GalleryManger extends Manager {

    private final List<String> urls = List.of(
            "AgACAgIAAxkBAAIERGfUSuMZVGvuVMyNSWClfedXqWffAAJh7jEb25GhSl_E5_DXPt3fAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIERmfUSvUsWPd1YEzfOZwD_04bm-2IAAJx7jEb25GhSqOM96eeWSY_AQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIESGfUSwE1IONGS-hgCd_AjF-MelJOAAJy7jEb25GhSqbUSIZRdrZTAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIESmfUSxIHQOSOj2KZaubaQ1VkqMEKAAJz7jEb25GhSvLC6wAB1kaWGwEAAwIAA3kAAzYE",
            "AgACAgIAAxkBAAIETGfUSx71Akqc6JBMJrJJfhH67-V4AAJ07jEb25GhSsmkvhAbZlbzAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIETmfUSyrnuGt6DrmDyy8x5NUFm9yGAAJ17jEb25GhSigJSTyIuejAAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIEUGfUSzb-ltJN6GH0vaHbK4VYICYlAAJ27jEb25GhStxxoLSvvRgmAQADAgADeAADNgQ",
            "AgACAgIAAxkBAAIEUmfUS0JBzrXdhrHqdpRZCpJsZx0HAAJ37jEb25GhSqEHecuDO07QAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIEVGfUS02zZYNij6a2ObpC2p1qv873AAJ47jEb25GhSlpqGE5I61aNAQADAgADeQADNgQ",
            "AgACAgIAAxkBAAIEVmfUS1g3J8dW42SoA-nfAieEWlvEAAJ57jEb25GhShZ9Np5roAPMAQADAgADeQADNgQ"
    );

    protected GalleryManger(TelegramBot bot) {
        super(bot);
    }

    @Override
    public BotApiMethod<?> answerCommand(Message message) {
        sendMediaGroup(message.getChatId(), urls);

        return sendMessage(message.getChatId());
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query) {
        sendMediaGroup(query.getMessage().getChatId(), urls);

        return sendMessage(query.getMessage().getChatId());
    }

    private void sendMediaGroup(Long chatId, List<String> urls) {
        if (urls == null || urls.isEmpty()) {
            return;
        }

        List<InputMedia> media = new ArrayList<>();

        for (String url : urls) {
            InputMediaPhoto photo = InputMediaPhoto.builder()
                    .media(url)
                    .build();
            media.add(photo);
        }

        SendMediaGroup sendMediaGroup = SendMediaGroup.builder()
                .chatId(chatId)
                .medias(media)
                .build();

        try {
            super.getBot().execute(sendMediaGroup);
        } catch (TelegramApiException e) {
            log.error("Error sending mediaGroup", e);
        }
    }

    private BotApiMethod<?> sendMessage(Long chatId) {
        return SendMessage.builder()
                .text(
                        """
                        ✨ Галерея работ 💅
                       
                        Здесь собраны мои лучшие работы — стильные, аккуратные и безупречные! 😍
                        
                        Каждый маникюр создан с любовью и вниманием к деталям, чтобы подчеркнуть вашу индивидуальность. ✨
                        
                        📸 Выбирайте дизайн, вдохновляйтесь и записывайтесь на маникюр! 💖
                        
                        Какой стиль тебе по душе? Напиши мне, и вместе создадим идеальный образ! 💅💕
                        """
                )
                .chatId(chatId)
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Назад ⬅️"),
                                List.of(1),
                                List.of(back.name())
                        )
                )
                .build();
    }
}
