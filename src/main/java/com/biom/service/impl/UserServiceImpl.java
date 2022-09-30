/*
package com.biom.service.impl;

import com.biom.entity.ERole;
import com.biom.entity.Role;
import com.biom.entity.User;
import com.biom.repository.RoleRepository;
import com.biom.repository.UserRepository;
import com.biom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(new Role(ERole.USER));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    @Override
    public List<User> usergtList(Long idMin) {
        return userRepository.getGtList(idMin);
    }
}
*/
