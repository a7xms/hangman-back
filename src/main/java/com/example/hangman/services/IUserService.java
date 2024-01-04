package com.example.hangman.services;

import com.example.hangman.dto.login.JwtAuthenticationResponse;
import com.example.hangman.dto.login.LoginRequest;
import com.example.hangman.dto.register.RegisterRequest;
import com.example.hangman.models.User;

import java.util.List;

public interface IUserService {

    void register(RegisterRequest request);
    JwtAuthenticationResponse login(LoginRequest request);
    User getCurrentUser();
    List<User> getAllUsers();
    void confirmEmail(String token);
}
