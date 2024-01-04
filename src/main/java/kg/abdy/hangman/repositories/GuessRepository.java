package kg.abdy.hangman.repositories;

import kg.abdy.hangman.models.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuessRepository extends JpaRepository<Guess, Long> {
}
