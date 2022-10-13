package com.biom.security.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Authorization {
    private final String userName;
    private final String password;
    public Authorization(@JsonProperty("username") String userName,
                         @JsonProperty("password") String password) {
        this.userName = userName;
        this.password = password;
    }
}
