package com.biom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserCreateDto {

    private final String email;
    private final String password;

    public UserCreateDto(@JsonProperty("email") String email,
                         @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

}
