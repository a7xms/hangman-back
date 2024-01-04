package kg.abdy.hangman.controllers;

import kg.abdy.hangman.dto.game.GameResponse;
import kg.abdy.hangman.dto.game.GuessRequest;
import kg.abdy.hangman.dto.game.GuessResponse;
import kg.abdy.hangman.services.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<GameResponse> startGame() {
        return new ResponseEntity<>(gameService.startGame(), HttpStatus.OK);
    }

    @PostMapping("/guess")
    public ResponseEntity<GuessResponse> guess(@RequestBody GuessRequest request) {
        return new ResponseEntity<>(gameService.guessLetter(request), HttpStatus.OK);
    }

}
