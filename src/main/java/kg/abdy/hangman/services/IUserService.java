package kg.abdy.hangman.services;

import kg.abdy.hangman.dto.login.JwtAuthenticationResponse;
import kg.abdy.hangman.dto.login.LoginRequest;
import kg.abdy.hangman.dto.register.RegisterRequest;
import kg.abdy.hangman.models.User;

import java.util.List;

public interface IUserService {

    void register(RegisterRequest request);
    JwtAuthenticationResponse login(LoginRequest request);
    User getCurrentUser();
    List<User> getAllUsers();
    void confirmEmail(String token);
}
