package ru.a.nail.telegram.bot.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.a.nail.telegram.bot.model.Handler;
import ru.a.nail.telegram.bot.model.Manager;
import ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData;

import java.util.Map;

@Slf4j
@Service
public class CallbackQueryHandler extends Handler<CallbackQuery> {

    protected CallbackQueryHandler(Map<String, Manager> managers) {
        super(managers);
    }

    @Override
    public BotApiMethod<?> answer(CallbackQuery query) {

        String[] data = query.getData().split("_");
        CommandAndQueryData queryData;

        try {
            queryData = CommandAndQueryData.valueOf(data[0]);
        } catch (Exception e) {
            log.error("Unsupported query data received: {}", query.getData());
            return unknownHandle(query.getMessage().getChatId(), query.getData());
        }

        if ("back".equals(queryData.name())) {
            return super.getManagers().get("menu").answerCommand((Message) query.getMessage());
        }

        Manager manager = super.getManagers().get(queryData.name());
        if (manager == null) {
            return unknownHandle(query.getMessage().getChatId(), query.getData());
        }
        log.info("User {} sent query is {} in chat {}",
                query.getFrom().getUserName(), queryData.name(), query.getMessage().getChatId());
        return manager.answerQuery(query);
    }
}
