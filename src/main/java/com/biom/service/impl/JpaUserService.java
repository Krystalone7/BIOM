package com.biom.service.impl;

import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.entity.User;
import com.biom.repository.UserRepository;
import com.biom.service.UserService;
import com.biom.service.mapper.UserListMapper;
import com.biom.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListMapper userListMapper;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = new User(
                userCreateDto.getEmail(),
                userCreateDto.getPassword()
        );

        user = userRepository.saveAndFlush(user);

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return userListMapper.toDtoList(users);
    }
}
