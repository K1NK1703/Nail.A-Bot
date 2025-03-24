package ru.a.nail.telegram.bot.service.entity;

import ru.a.nail.telegram.bot.entity.User;

public interface UserService {
    User registerUser(String username, Long chatId);
}
