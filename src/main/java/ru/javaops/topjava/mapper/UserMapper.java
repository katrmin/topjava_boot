package ru.javaops.topjava.mapper;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javaops.topjava.dto.UserDto;
import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.UserRole;

import java.util.Set;

public class UserMapper {
    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static User map(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail().toLowerCase())
                .password(PASSWORD_ENCODER.encode(userDto.getPassword()))
                .roles(Set.of(UserRole.builder().role(Role.USER).build()))
                .name(userDto.getName())
                .id(userDto.getId())
                .build();
    }

    public static User updateFromDto(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail().toLowerCase());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
