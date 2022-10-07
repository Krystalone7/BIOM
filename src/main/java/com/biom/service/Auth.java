package com.biom.service;


import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.security.data.Authorization;
import com.biom.security.data.Token;

public interface Auth {
    Token signIn(Authorization authorization);
    UserDto registration(UserCreateDto userCreateDto);
}
