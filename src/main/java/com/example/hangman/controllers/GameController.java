package com.example.hangman.controllers;

import com.example.hangman.dto.game.GameResponse;
import com.example.hangman.dto.game.GuessRequest;
import com.example.hangman.dto.game.GuessResponse;
import com.example.hangman.services.IGameService;
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
