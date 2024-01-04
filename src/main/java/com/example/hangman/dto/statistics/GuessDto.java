package com.example.hangman.dto.statistics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuessDto {

    private String letter;
    private Boolean correct;

}
