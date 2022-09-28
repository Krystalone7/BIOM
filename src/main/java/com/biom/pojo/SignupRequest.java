package com.biom.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String info;
    private String hobbies;
    private String phone;
    private String email;
    private String password;
    private Set<String> roles;
}
