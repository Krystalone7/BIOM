package com.biom.service.impl;

import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.entity.Role;
import com.biom.entity.User;
import com.biom.repository.RoleRepository;
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

@Service
@RequiredArgsConstructor
public class AuthImpl implements Auth {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtActions jwtActions;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public Token signIn(Authorization authorization) {

        User user = userRepository.findByUsername(authorization.getUsername()).orElseThrow();

        UserContext userContext = new UserContext(
                user.getUsername(),
                user.getPassword(),
                user.getRoles());

        if (passwordEncoder.matches(authorization.getPassword(), user.getPassword())) {
            return new Token(jwtActions.createToken(userContext));
        } else {
            throw new RuntimeException("Аутентификация не пройдена!");
        }
    }

    @Override
    public UserDto registration(UserCreateDto userCreateDto) {

        Role role = roleRepository.getById(2L);
        User user = new User(
                userCreateDto.getUsername(),
                userCreateDto.getName(),
                userCreateDto.getSurname(),
                userCreateDto.getBirthdate(),
                userCreateDto.getEmail(),
                passwordEncoder.encode(userCreateDto.getPassword())
        );

        user = userRepository.saveAndFlush(user);

        return userMapper.toDto(user);
    }
}
