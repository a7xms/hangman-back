package com.example.hangman.dto.catalog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWordRequest {
    private String letters;
    private Integer attempts;
}
