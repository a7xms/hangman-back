package com.example.hangman.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "words",
        indexes = @Index(name = "letters_index", columnList = "letters", unique = true)
)
@Getter
@Setter
public class Word extends BaseModel{

    private String letters;
    private Integer attempts;

}
