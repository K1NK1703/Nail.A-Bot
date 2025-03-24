package ru.a.nail.telegram.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.a.nail.telegram.bot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndChatId(String username, Long chatId);
}
