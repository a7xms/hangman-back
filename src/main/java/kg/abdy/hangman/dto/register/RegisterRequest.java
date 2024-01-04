package kg.abdy.hangman.dto.register;

import kg.abdy.hangman.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
}
