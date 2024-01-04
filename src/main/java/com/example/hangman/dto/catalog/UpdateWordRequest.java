package com.example.hangman.dto.catalog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWordRequest {
    private Long id;
    private String letters;
    private Integer attempts;

}
