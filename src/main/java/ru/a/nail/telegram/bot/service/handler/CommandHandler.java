package ru.a.nail.telegram.bot.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.a.nail.telegram.bot.model.Handler;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData;

import java.util.Map;

@Slf4j
@Service
public class CommandHandler extends Handler<Message> {

    protected CommandHandler(Map<String, Manager> managers) {
        super(managers);
    }

    @Override
    public BotApiMethod<?> answer(Message message) {
        String command = message.getText().substring(1);
        CommandAndQueryData commandData;

        try {
            commandData = CommandAndQueryData.valueOf(command);
        } catch (Exception e) {
            log.warn("Unsupported command was received: {}", command);
            return unknownHandle(message.getChatId(), command);
        }

        if ("help".equals(commandData.name())) {
            return showListOfCommands(message.getChatId());
        }

        Manager manager = super.getManagers().get(commandData.name());
        if (manager == null) {
            return unknownHandle(message.getChatId(), command);
        }
        log.info("User {} select command is {} in chat {}",
                message.getFrom().getUserName(), commandData.name(), message.getChatId());
        return manager.answerCommand(message);
    }
}
