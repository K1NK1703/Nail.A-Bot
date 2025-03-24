package ru.a.nail.telegram.bot.service.manager;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.a.nail.telegram.bot.utils.KeyboardFactory;
import ru.a.nail.telegram.bot.model.TelegramBot;
import ru.a.nail.telegram.bot.model.Manager;

import java.util.List;

import static ru.a.nail.telegram.bot.utils.enums.CommandAndQueryData.menu;

@Service("advice")
public class AdviceManager extends Manager {

    protected AdviceManager(TelegramBot bot) {
        super(bot);
    }

    @Override
    public BotApiMethod<?> answerCommand(Message message) {
        return SendMessage.builder()
                .text(
                        """
                        🌟 Советы для безупречного маникюра 🌟
                        
                        1️⃣ Избегайте контакта с агрессивными веществами – при уборке или мытье посуды всегда используйте перчатки. Берегите ваши ноготки! 💅✨
                        
                        2️⃣ Не используйте ногти как инструмент – не поддевайте крышки и не скребите поверхности. Ногти – это эстетика, а не инструменты! 😉
                        
                        3️⃣ Увлажняйте кутикулу и кожу рук – каждый день наносите масло для кутикулы и питательный крем. Ваши руки заслуживают заботы! 💖
                        
                        4️⃣ Осторожно с перепадами температуры – горячая вода и мороз могут повредить покрытие. Не рискуйте! 🌡️
                        
                        5️⃣ Не грызите и не отрывайте гель – если нужно снять покрытие, доверьтесь мастеру или используйте специальное средство. 💅
                        
                        6️⃣ При сколах не пытайтесь исправить их самостоятельно – лучше обратиться к профессионалу, чтобы сохранить идеальный вид маникюра! 💅
                       
                        💖 С правильным уходом ваш маникюр останется безупречным, а ногти здоровыми на долгое время! ✨
                        """
                )
                .chatId(message.getChatId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Назад ⬅️"),
                                List.of(1),
                                List.of(menu.name())
                        )
                )
                .build();
    }

    @Override
    public BotApiMethod<?> answerQuery(CallbackQuery query) {
        return EditMessageText.builder()
                .text(
                        """
                        🌟 Советы для безупречного маникюра 🌟
                        
                        1️⃣ Избегайте контакта с агрессивными веществами – при уборке или мытье посуды всегда используйте перчатки. Берегите ваши ноготки! 💅✨
                        
                        2️⃣ Не используйте ногти как инструмент – не поддевайте крышки и не скребите поверхности. Ногти – это эстетика, а не инструменты! 😉
                        
                        3️⃣ Увлажняйте кутикулу и кожу рук – каждый день наносите масло для кутикулы и питательный крем. Ваши руки заслуживают заботы! 💖
                        
                        4️⃣ Осторожно с перепадами температуры – горячая вода и мороз могут повредить покрытие. Не рискуйте! 🌡️
                        
                        5️⃣ Не грызите и не отрывайте гель – если нужно снять покрытие, доверьтесь мастеру или используйте специальное средство. 💅
                        
                        6️⃣ При сколах не пытайтесь исправить их самостоятельно – лучше обратиться к профессионалу, чтобы сохранить идеальный вид маникюра! 💅
                       
                        💖 С правильным уходом ваш маникюр останется безупречным, а ногти здоровыми на долгое время! ✨
                        """
                )
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(
                        KeyboardFactory.getInlineKeyboard(
                                List.of("Назад ⬅️"),
                                List.of(1),
                                List.of(menu.name())
                        )
                )
                .build();
    }
}
