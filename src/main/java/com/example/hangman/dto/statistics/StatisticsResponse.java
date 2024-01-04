package com.example.hangman.dto.statistics;

import com.example.hangman.enums.GameResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StatisticsResponse {

    private Long gameId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private LocalDateTime finish;
    private String word;
    private GameResult result;
    private List<GuessDto> guessedLetters;
    private String fullName;

}
