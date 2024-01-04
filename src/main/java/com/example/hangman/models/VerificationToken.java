package com.example.hangman.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken extends BaseModel{
    private static final int EXPIRATION = 60 * 24;

    private String token;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    private LocalDateTime calculateExpiryDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.plusMinutes(VerificationToken.EXPIRATION);
    }

    public VerificationToken(String token, User user) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate();
    }
}
