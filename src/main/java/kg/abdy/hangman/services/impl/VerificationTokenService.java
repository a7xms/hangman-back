package kg.abdy.hangman.services.impl;

import kg.abdy.hangman.models.User;
import kg.abdy.hangman.models.VerificationToken;
import kg.abdy.hangman.repositories.VerificationTokenRepository;
import kg.abdy.hangman.services.IVerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenService implements IVerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken createToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public Optional<VerificationToken> getVerificationToken(String token) {
        return verificationTokenRepository
                .findByToken(token);
    }
}
