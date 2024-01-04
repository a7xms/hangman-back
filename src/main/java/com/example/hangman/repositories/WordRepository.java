package com.example.hangman.repositories;

import com.example.hangman.models.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    Page<Word> findByLettersContainingOrderByLettersAsc(String query, Pageable pageable);

    @Query(value = "select * from words order by random() limit 1", nativeQuery = true)
    Word getRandomWord();

    boolean existsByLetters(String letters);

    boolean existsByLettersAndIdNot(String letters, Long id);

}
