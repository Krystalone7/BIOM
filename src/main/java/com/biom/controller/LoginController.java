package com.biom.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/user")
    public String user(Authentication authentication) {
        System.out.println((UserDetails) authentication.getPrincipal());
        return "login";
    }
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
    @GetMapping("/")
    public String welcome() {
        return "Welcome";
    }
}
