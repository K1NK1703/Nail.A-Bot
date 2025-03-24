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
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.model.TelegramBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.back;

@Slf4j
@Service("contact")
public class ContactManager extends Manager {

    private final Map<String, List<String>> urls = Map.of(
            "Photo", List.of(
                    "AgACAgIAAxkBAAIDZmfUKQJnqUxgNzlbhuGmjv1FEtIbAALU7DEb25GhShPOd_vSC0HGAQADAgADeQADNgQ"
            ),
            "Video", List.of(
                    "BAACAgIAAxkBAAIDa2fUK0JxsrF2xjfJ_lphsbBuwLmAAAJRcAAC25GhSrWNJy_eMnZcNgQ",
                    "BAACAgIAAxkBAAIDbGfUK4MWQ9bIJZWwddoscL2bEPBdAAJScAAC25GhSsJzk7RNsiXLNgQ"
            )
    );

    protected ContactManager(TelegramBot bot) {
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

    private BotApiMethod<?> sendMessage(Long chatId) {
        return SendMessage.builder()
                .text(
                        """
                        📍 Где находится студия? 💅✨
                        
                        🏠 Адрес: проспект КИМа, 6 (м. Приморская 💚)
                        🚪 Вход: со стороны ул. Одоевской
                        ➡️ Ориентир: серая дверь справа от шиномонтажки
                        🔝 Этаж: 2-й, студия 280
                        
                        📲 Telegram: @gagushichewa
                        💌 Telegram-канал: @nailsaaaa
                        📸 Instagram: @nails.gag
                        
                        Тебя ждёт уютная студия, в которой ты создашь свой безупречный маникюр! 💖✨
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

    private void sendMediaGroup(Long chatId, Map<String, List<String>> urls) {
        if (urls == null || urls.isEmpty()) {
            return;
        }

        List<InputMedia> media = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : urls.entrySet()) {
            String mediaType = entry.getKey();
            List<String> mediaIds = entry.getValue();

            if ("photo".equalsIgnoreCase(mediaType)) {
                for (String mediaId : mediaIds) {
                    InputMediaPhoto photo = InputMediaPhoto.builder()
                            .media(mediaId)
                            .build();
                    media.addFirst(photo);
                }
            } else if ("video".equalsIgnoreCase(mediaType)) {
                for (String mediaId : mediaIds) {
                    InputMediaVideo video = InputMediaVideo.builder()
                            .media(mediaId)
                            .build();
                    media.add(video);
                }
            } else {
                log.warn("Unsupported media type for URL: {}", mediaType);
            }
        }

        if (media.isEmpty()) {
            log.warn("No supported media found for chatId {}", chatId);
            return;
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
}
