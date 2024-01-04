package kg.abdy.hangman.services;

import kg.abdy.hangman.dto.game.GameResponse;
import kg.abdy.hangman.dto.game.GuessRequest;
import kg.abdy.hangman.dto.game.GuessResponse;

public interface IGameService {
    GameResponse startGame();

    GuessResponse guessLetter(GuessRequest request);
}
