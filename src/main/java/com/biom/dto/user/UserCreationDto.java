package com.biom.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserCreationDto {
    private final String name;
    private final String surname;
    private final LocalDate birthdate;
    private final String info;
    private final String hobbies;
    private final String phone;

    @JsonCreator
    public UserCreationDto(@JsonProperty String name,
                           @JsonProperty String surname,
                           @JsonProperty LocalDate birthdate,
                           @JsonProperty String info,
                           @JsonProperty String hobbies,
                           @JsonProperty String phone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.info = info;
        this.hobbies = hobbies;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getInfo() {
        return info;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getPhone() {
        return phone;
    }
}
