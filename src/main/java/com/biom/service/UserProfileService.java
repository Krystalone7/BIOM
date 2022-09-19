package com.biom.service;

import com.biom.dto.user.UserCreationDto;
import com.biom.entity.UserProfile;
import com.biom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserProfileService {

    UserProfile addUser(UserCreationDto userCreationDto);

    List<UserProfile> getAll();
}
