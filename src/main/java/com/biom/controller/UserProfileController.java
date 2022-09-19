package com.biom.controller;

import com.biom.dto.ResponseDto;
import com.biom.dto.user.UserCreationDto;
import com.biom.entity.UserProfile;
import com.biom.service.UserProfileService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping("all")
    public ResponseDto<List<UserProfile>> getAll(){
        return new ResponseDto<>("OK", "OK", service.getAll());
    }

    @PostMapping("add")
    public ResponseDto<UserProfile> addUser(@RequestBody UserCreationDto userCreationDto){
        return new ResponseDto<>("OK", "OK", service.addUser(userCreationDto));
    }

}
