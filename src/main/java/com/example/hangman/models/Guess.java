package com.example.hangman.models;

import com.example.hangman.models.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "guesses",
        indexes = @Index(name = "game_id_index", columnList = "game_id")
)
@Getter
@Setter
@NoArgsConstructor
public class Guess {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String letter;

    private Boolean correct;

}
