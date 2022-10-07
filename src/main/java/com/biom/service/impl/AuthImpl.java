package com.biom.service.impl;

import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.entity.Role;
import com.biom.entity.User;
import com.biom.repository.UserRepository;
import com.biom.security.JwtActions;
import com.biom.security.context.UserContext;
import com.biom.security.data.Authorization;
import com.biom.security.data.Token;
import com.biom.service.Auth;
import com.biom.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthImpl implements Auth {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtActions jwtActions;
    private final UserMapper userMapper;

    @Override
    public Token signIn(Authorization authorization) {
        User user = userRepository.findByEmail(authorization.getUserName()).orElseThrow();
        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        UserContext userContext = new UserContext(
                user.getEmail(),
                user.getPassword(),
                roles);

        if (passwordEncoder.matches(authorization.getPassword(), user.getPassword())) {
            return new Token(jwtActions.createToken(userContext));
        } else {
            throw new RuntimeException("Аутентификация не пройдена!");
        }
    }

    @Override
    public UserDto registration(UserCreateDto userCreateDto) {
        User user = new User(
                userCreateDto.getEmail(),
                passwordEncoder.encode(userCreateDto.getPassword())
        );
        user.setRoles(Collections.singleton(new Role("user")));

        user = userRepository.saveAndFlush(user);

        return userMapper.toDto(user);
    }
}
