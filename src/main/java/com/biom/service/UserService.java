package com.biom.service;

import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserCreateDto userCreateDto);
    List<UserDto> getAllUser();
}
