package com.biom.controller;

import com.biom.dto.UserCreateDto;
import com.biom.dto.UserDto;
import com.biom.service.Auth;
import com.biom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Auth auth;

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/get-all")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/create")
    public UserDto createUser(UserCreateDto userCreateDto) {
        return auth.registration(userCreateDto);
    }
}
