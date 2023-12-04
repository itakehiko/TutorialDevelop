package com.techacademy.service;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
