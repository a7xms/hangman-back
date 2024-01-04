package com.example.hangman.services;

import com.example.hangman.dto.game.GameResponse;
import com.example.hangman.dto.game.GuessRequest;
import com.example.hangman.dto.game.GuessResponse;

public interface IGameService {
    GameResponse startGame();

    GuessResponse guessLetter(GuessRequest request);
}
