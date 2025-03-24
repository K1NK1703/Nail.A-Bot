package ru.a.nail.telegram.bot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
//import ru.a.nail.telegram.bot.service.entity.UserService;
import ru.a.nail.telegram.bot.service.handler.CallbackQueryHandler;
import ru.a.nail.telegram.bot.service.handler.CommandHandler;
import ru.a.nail.telegram.bot.service.handler.MessageHandler;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

    // TODO: Улучшить логику (вынести в другое место) добавления пользователя в БД
    //UserService userService;
    CommandHandler commandHandler;
    MessageHandler messageHandler;
    CallbackQueryHandler queryHandler;

    public BotApiMethod<?> distribute(Update update) {
        if (update.hasCallbackQuery()) {
            /*saveAndLoggingUser(
                    update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getMessage().getChatId()
            );*/
            return queryHandler.answer(update.getCallbackQuery());
        }
        if (update.hasMessage()) {
            /*saveAndLoggingUser(
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getChatId()
            );*/

            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.charAt(0) == '/') {
                    return commandHandler.answer(update.getMessage());
                }
                return messageHandler.answer(update.getMessage());
            }
        }
        log.warn("Unsupported update type: {}", update);
        return null;
    }

    /*private void saveAndLoggingUser(String username, Long chatId) {
        log.info(userService.registerUser(username, chatId).toString());
    }*/
}
