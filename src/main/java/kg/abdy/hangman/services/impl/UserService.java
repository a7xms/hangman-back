package kg.abdy.hangman.services.impl;

import kg.abdy.hangman.dto.login.JwtAuthenticationResponse;
import kg.abdy.hangman.dto.login.LoginRequest;
import kg.abdy.hangman.dto.register.RegisterRequest;
import kg.abdy.hangman.exceptions.BadRequestException;
import kg.abdy.hangman.exceptions.ResourceNotFoundException;
import kg.abdy.hangman.models.User;
import kg.abdy.hangman.models.VerificationToken;
import kg.abdy.hangman.repositories.UserRepository;
import kg.abdy.hangman.security.CustomUserDetails;
import kg.abdy.hangman.security.JwtTokenProvider;
import kg.abdy.hangman.services.IMailSender;
import kg.abdy.hangman.services.IUserService;
import kg.abdy.hangman.services.IVerificationTokenService;
import kg.abdy.hangman.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider authenticationProvider;
    private final JwtTokenProvider tokenProvider;
    private final IVerificationTokenService IVerificationTokenService;
    private final IMailSender IMailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       DaoAuthenticationProvider authenticationProvider,
                       JwtTokenProvider tokenProvider,
                       IVerificationTokenService IVerificationTokenService,
                       IMailSender IMailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.tokenProvider = tokenProvider;
        this.IVerificationTokenService = IVerificationTokenService;
        this.IMailSender = IMailSender;
    }

    @Override
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("User already exists!");
        }
        User user = buildUser(request);
        VerificationToken verificationToken = IVerificationTokenService
                .createToken(userRepository.save(user));
        String toEmail = user.getEmail();
        String subject = "Confirm your email!";
        String content = MailUtils.constructConfirmMailMessage(verificationToken);

        IMailSender.sendMail(subject, toEmail, content);

    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        String token = tokenProvider
                .generateToken(authenticationProvider.authenticate(authentication));

        return new JwtAuthenticationResponse(token);

    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userDetails.getUsername()));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void confirmEmail(String token) {
        Optional<VerificationToken> verificationToken = IVerificationTokenService
                .getVerificationToken(token);

        if(verificationToken.isEmpty()) {
            throw new BadRequestException("Invalid token!");
        }
        if(verificationToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token expired!");
        }
        User user = verificationToken.get().getUser();
        user.setEmailConfirmed(true);
        userRepository.save(user);
    }

    private User buildUser(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUserRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return user;
    }


}
