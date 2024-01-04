package com.example.hangman.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameResponse {
    private Long gameId;
    private String secretWord;
    private Integer attempts;
}
