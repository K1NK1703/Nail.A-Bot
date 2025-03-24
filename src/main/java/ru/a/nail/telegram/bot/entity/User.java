package ru.a.nail.telegram.bot.entity;

import jakarta.persistence.Enumerated;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.a.nail.telegram.bot.utils.enums.RoleNameEnum;

import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_seq", initialValue = 3, allocationSize = 1)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    Long chatId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    RoleNameEnum role;

    @Column(nullable = false)
    Boolean blocked;

    @Builder
    public User(String username, Long chatId, Boolean blocked) {
        this.username = username;
        this.chatId = chatId;
        this.blocked = Optional.ofNullable(blocked).orElse(Boolean.FALSE);
        this.role = RoleNameEnum.USER;
    }

    @Override
    public String toString() {
        return "User: {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", chatId=" + chatId +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
