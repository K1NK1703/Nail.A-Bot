package ru.a.nail.telegram.bot.utils.wrapper;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendPhotoWrapper extends BotApiMethod<Message> {

    SendPhoto sendPhoto;

    @Override
    public String getMethod() {
        return sendPhoto.getMethod();
    }

    @Override
    public Message deserializeResponse(String response) throws TelegramApiRequestException {
        return sendPhoto.deserializeResponse(response);
    }
}
