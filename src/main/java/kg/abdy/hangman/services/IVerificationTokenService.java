package kg.abdy.hangman.services;

import kg.abdy.hangman.models.User;
import kg.abdy.hangman.models.VerificationToken;

import java.util.Optional;

public interface IVerificationTokenService {
    VerificationToken createToken(User user);
    Optional<VerificationToken> getVerificationToken(String token);
}
