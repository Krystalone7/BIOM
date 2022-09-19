package com.biom.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "info")
    private String info;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "phone")
    private String phone;

    public UserProfile() {
    }

    public UserProfile(String name, String surname, LocalDate birthdate, String info, String hobbies, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.info = info;
        this.hobbies = hobbies;
        this.phone = phone;
    }

}
