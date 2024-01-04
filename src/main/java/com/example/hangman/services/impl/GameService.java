package com.example.hangman.services.impl;

import com.example.hangman.dto.game.GameResponse;
import com.example.hangman.dto.game.GuessRequest;
import com.example.hangman.dto.game.GuessResponse;
import com.example.hangman.enums.GameResult;
import com.example.hangman.exceptions.ResourceNotFoundException;
import com.example.hangman.models.Game;
import com.example.hangman.models.Guess;
import com.example.hangman.models.Word;
import com.example.hangman.repositories.GameRepository;
import com.example.hangman.repositories.GuessRepository;
import com.example.hangman.repositories.WordRepository;
import com.example.hangman.services.IGameService;
import com.example.hangman.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameService implements IGameService {

    private final GameRepository gameRepository;
    private final WordRepository wordRepository;
    private final IUserService userService;
    private final GuessRepository guessRepository;

    @Autowired
    public GameService(GameRepository gameRepository,
                       WordRepository wordRepository,
                       IUserService userService,
                       GuessRepository guessRepository) {
        this.gameRepository = gameRepository;
        this.wordRepository = wordRepository;
        this.userService = userService;
        this.guessRepository = guessRepository;
    }


    @Override
    public GameResponse startGame() {
        Word word = getRandomWord();
        Integer attempts = word.getAttempts();
        String letters = word.getLetters();
        int length = letters.length();

        Game game = new Game();
        game.setUser(userService.getCurrentUser());
        game.setActualWord(letters);
        game.setSecretWord("_".repeat(length));
        game.setRemainingAttempts(attempts);
        gameRepository.save(game);

        return new GameResponse(game.getId(), game.getSecretWord(), game.getRemainingAttempts());

    }

    @Override
    public GuessResponse guessLetter(GuessRequest request) {
        Game game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", request.getGameId()));

        String word = game.getActualWord();
        String secretWord = game.getSecretWord();
        String letter = request.getLetter().toUpperCase();
        Integer remainingAttempts = game.getRemainingAttempts();

        Guess guess = new Guess();
        guess.setGame(game);
        guess.setLetter(letter);

        if(word.contains(letter)) {
            guess.setCorrect(true);
            String newSecretWord = rewriteSecretWord(word, secretWord, letter);
            game.setSecretWord(newSecretWord);

            if(newSecretWord.equals(word)) {
                game.setResult(GameResult.WIN);
            }
        }
        else {
            guess.setCorrect(false);
            game.setRemainingAttempts(remainingAttempts - 1);

            if(remainingAttempts == 1) {
                game.setSecretWord(word);
                game.setResult(GameResult.LOSE);
            }
        }

        gameRepository.save(game);
        guessRepository.save(guess);

        return new GuessResponse(
                game.getSecretWord(),
                game.getRemainingAttempts(),
                guess.getCorrect(),
                game.getResult());
    }

    private Word getRandomWord() {
        return wordRepository.getRandomWord();
    }

    private String rewriteSecretWord(String word, String secretWord, String letter) {
        String[] letters = word.split("");
        String[] secretLetters = secretWord.split("");

        for(int i = 0; i < letters.length; i++) {
            if(Objects.equals(letters[i], letter)) {
                secretLetters[i] = letter;
            }
        }
        return String.join("", secretLetters);
    }
}
