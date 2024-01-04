package kg.abdy.hangman.dto.game;


import kg.abdy.hangman.enums.GameResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuessResponse {
    private String secretWord;
    private Integer remainingAttempts;
    private Boolean correct;
    private GameResult result;
}
