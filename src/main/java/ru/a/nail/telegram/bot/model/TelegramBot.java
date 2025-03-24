package ru.a.nail.telegram.bot.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;
import ru.a.nail.telegram.bot.config.TelegramBotConfig;
import ru.a.nail.telegram.bot.service.UpdateDispatcher;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TelegramBot extends TelegramWebhookBot {

    TelegramBotConfig config;
    UpdateDispatcher updateDispatcher;

    public TelegramBot(TelegramBotConfig config, @Lazy UpdateDispatcher updateDispatcher) {
        super(config.getBotToken());
        this.config = config;
        this.updateDispatcher = updateDispatcher;
    }

    @Override
    public String getBotUsername() {
        return config.getBotUsername();
    }

    @Override
    public String getBotPath() {
        return config.getWebhookPath();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        /*if (update.hasMessage() && update.getMessage().hasPhoto()) {
            return handleMedia(update);
        }*/
        return updateDispatcher.distribute(update);
    }

    /*private BotApiMethod<?> handleMedia(Update update) {
        String fileIdPhoto = extractPhotoFileId(update);
        String fileIdVideo = extractVideoFileId(update);

        if (fileIdPhoto != null || fileIdVideo != null) {
            return SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text("Ваш fileIdPhoto: " + fileIdPhoto + "\nВаш fileIdVideo: " + fileIdVideo)
                    .build();
        }

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Отправьте фото, чтобы получить fileId.")
                .build();
    }

    private String extractPhotoFileId(Update update) {
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            List<PhotoSize> photos = update.getMessage().getPhoto();
            return photos.getLast().getFileId();
        }
        return null;
    }

    private String extractVideoFileId(Update update) {
        if (update.hasMessage() && update.getMessage().hasVideo()) {
            Video video = update.getMessage().getVideo();
            return video.getFileId();
        }
        return null;
    }*/
}
