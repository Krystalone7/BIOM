package com.biom.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user", schema = "biom")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "id"))
    Set<Role> role = new HashSet<>();
    public User() {
    }

    public User(String username, String name, String surname, LocalDate birthdate, String info, String hobbies, String phone, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.info = info;
        this.hobbies = hobbies;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
