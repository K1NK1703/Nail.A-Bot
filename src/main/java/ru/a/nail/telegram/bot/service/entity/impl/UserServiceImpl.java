package ru.a.nail.telegram.bot.service.entity.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.a.nail.telegram.bot.entity.User;
import ru.a.nail.telegram.bot.repository.UserRepository;
import ru.a.nail.telegram.bot.service.entity.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(String username, Long chatId) {
        if (username == null || username.isEmpty() || username.isBlank() ||
                chatId == null || chatId < 0) {
            username = "Invalid username";
        }
        String finalUsername = "@" + username;

        return userRepository.findByUsernameAndChatId(finalUsername, chatId)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .username(finalUsername)
                                .chatId(chatId)
                                .build()
                    )
                );
    }
}
