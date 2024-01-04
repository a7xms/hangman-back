package com.example.hangman.controllers;

import com.example.hangman.dto.login.JwtAuthenticationResponse;
import com.example.hangman.dto.login.LoginRequest;
import com.example.hangman.dto.register.RegisterRequest;
import com.example.hangman.exceptions.BadRequestException;
import com.example.hangman.models.User;
import com.example.hangman.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService IUserService;

    @Autowired
    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        IUserService.register(request);
        return new ResponseEntity<>("Registration successful!!!", HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        try {
            return new ResponseEntity<>(IUserService.login(request), HttpStatus.OK);
        }
        catch (AuthenticationException e) {
            throw new BadRequestException("Username or password is incorrect!");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(IUserService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/auth/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam String token) {
        IUserService.confirmEmail(token);
        return new ResponseEntity<>("Email successfully confirmed", HttpStatus.OK);
    }

    @GetMapping("/user/info")
    public ResponseEntity<User> getCurrentUser() {
        return new ResponseEntity<>(IUserService.getCurrentUser(), HttpStatus.OK);
    }



}
