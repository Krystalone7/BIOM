package com.biom.controller;

import com.biom.cookie.CookieToken;
import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.security.data.Authorization;
import com.biom.security.data.Token;
import com.biom.service.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final Auth auth;
    private final CookieToken cookieToken;

    @PostMapping("/registration")
    public UserDto createUser( UserCreateDto userCreateDto) {
        return auth.registration(userCreateDto);
    }

    @PostMapping("/auth")
    public String signIn(HttpServletResponse response, Authorization authorization) {
        Token token = auth.signIn(authorization);
        cookieToken.createCookieToken(response, token.getToken());

        return "Вы успешно вошли в систему!";
    }
}
