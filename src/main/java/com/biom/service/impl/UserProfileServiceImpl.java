package com.biom.service.impl;

import com.biom.controller.advice.exception.UserProfileNotExistException;
import com.biom.dto.user.UserCreationDto;
import com.biom.entity.UserProfile;
import com.biom.repository.UserRepository;
import com.biom.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserRepository userRepository;

    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfile addUser(UserCreationDto userCreationDto){
        return userRepository.save(
                new UserProfile(
                        userCreationDto.getName(),
                        userCreationDto.getSurname(),
                        userCreationDto.getBirthdate(),
                        userCreationDto.getInfo(),
                        userCreationDto.getHobbies(),
                        userCreationDto.getPhone()
                )
        );
    }
    @Override
    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }
}