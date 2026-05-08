package com.todo.backend.service;

import com.todo.backend.dto.LoginRequest;
import com.todo.backend.dto.RegisterRequest;
import com.todo.backend.entity.User;
import com.todo.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request)
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

    public String login(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return "Invalid email or password";
        }

        User user = optionalUser.get();

        boolean isPasswordCorrect =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordCorrect) {
            return "Invalid email or password";
        }

        return "Login successful";
    }
}
