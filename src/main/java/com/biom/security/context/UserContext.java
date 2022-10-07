package com.biom.security.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UserContext {

    private final String email;

    private final String password;

    private final Set<String> roles;
}
