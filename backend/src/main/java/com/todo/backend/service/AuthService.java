package com.todo.backend.service;

import com.todo.backend.dto.RegisterRequest;
import com.todo.backend.entity.User;
import com.todo.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String Register(RegisterRequest request)
    {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            return "Email Already Exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setCreated_at(LocalDateTime.now());

        userRepository.save(user);

        return "User Registered Succesfully";
    }
}
