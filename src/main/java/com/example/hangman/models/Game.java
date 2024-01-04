package com.example.hangman.models;

import com.example.hangman.enums.GameResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(
        name = "games",
        indexes = {
                @Index(name = "word_index", columnList = "actual_word"),
                @Index(name = "user_id_index", columnList = "user_id")}
)
@Getter
@Setter
public class Game extends BaseModel{

    @Column(name = "actual_word")
    private String actualWord;

    @Column(name = "secret_word")
    private String secretWord;

    @Column(name = "remaining_attempts")
    private Integer remainingAttempts;

    @OneToMany(mappedBy = "game")
    @OrderBy("id asc ")
    private Set<Guess> guesses;

    @Enumerated(EnumType.STRING)
    private GameResult result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
