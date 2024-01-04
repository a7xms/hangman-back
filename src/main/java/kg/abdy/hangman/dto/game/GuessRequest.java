package kg.abdy.hangman.dto.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuessRequest {

    private String letter;
    private Long gameId;

}
