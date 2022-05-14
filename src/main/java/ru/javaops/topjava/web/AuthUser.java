package ru.javaops.topjava.web;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.UserRole;

import java.util.stream.Collectors;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList()));
        this.user = user;
    }

    public Long id() {
        return user.id();
    }
}