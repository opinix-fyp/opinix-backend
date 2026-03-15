package com.opinix.backend.service;

import com.opinix.backend.dto.LoginRequest;
import com.opinix.backend.dto.LoginResponse;
import com.opinix.backend.dto.RegisterRequest;
import com.opinix.backend.dto.UserResponse;
import com.opinix.backend.model.Role;
import com.opinix.backend.model.User;
import com.opinix.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    //TODO: implement forgot password functionality
    //TODO: if we implement google api, we could impliment email verification.

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){ //constructor
        this.userRepository = userRepository;
    }

    public UserResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use"); //later can be changed to a custom exception
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword()); //hash later after prototype success
        user.setFullName(request.getFullName());
        user.setActive(true);
        user.setRole(Role.RESPONDENT); //default role, because everyone is a respondent unless youre an admin...
        //TODO implement a way for us to set users as admin / allow admins to set other users as admin

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFullName(savedUser.getFullName());
        response.setRole(savedUser.getRole());
        response.setActive(savedUser.isActive());
        return response;
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(user == null || !user.getPasswordHash().equals(request.getPassword())){
            throw new RuntimeException("Invalid email or password"); //later can be changed to a custom exception
        }

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful");
        //later to add token, tokenType, etc
        return response;
    }
}
