package com.example.hangman.services;

import com.example.hangman.models.User;
import com.example.hangman.models.VerificationToken;

import java.util.Optional;

public interface IVerificationTokenService {
    VerificationToken createToken(User user);
    Optional<VerificationToken> getVerificationToken(String token);
}
