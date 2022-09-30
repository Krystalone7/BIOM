package com.biom.service.impl;

import com.biom.entity.Role;
import com.biom.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String info;
    private String hobbies;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String name, String surname, LocalDate birthdate, String info, String hobbies, String phone, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.info = info;
        this.hobbies = hobbies;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthdate(),
                user.getInfo(),
                user.getHobbies(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
