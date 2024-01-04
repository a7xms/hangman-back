package com.example.hangman.services.impl;

import com.example.hangman.dto.PagedResponse;
import com.example.hangman.dto.catalog.CreateWordRequest;
import com.example.hangman.dto.catalog.UpdateWordRequest;
import com.example.hangman.exceptions.BadRequestException;
import com.example.hangman.exceptions.ResourceNotFoundException;
import com.example.hangman.models.Word;
import com.example.hangman.repositories.WordRepository;
import com.example.hangman.services.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatalogService implements ICatalogService {

    private final WordRepository wordRepository;

    @Autowired
    public CatalogService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word createWord(CreateWordRequest request) {
        String letters = request.getLetters().toUpperCase();
        if(wordRepository.existsByLetters(letters)) {
            throw new BadRequestException(String.format("Word %s already exists!", letters));
        }
        Word word = new Word();
        word.setLetters(letters);
        word.setAttempts(request.getAttempts());

        return wordRepository.save(word);
    }

    @Override
    public Word updateWord(UpdateWordRequest request) {
        String letters = request.getLetters().toUpperCase();
        if(wordRepository.existsByLettersAndIdNot(letters, request.getId())) {
            throw new BadRequestException(String.format("Word %s already exists!", letters));
        }
        Word word = wordRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Word", "id", request.getId()));
        word.setLetters(letters);
        word.setAttempts(request.getAttempts());

        return wordRepository.save(word);
    }

    @Override
    public PagedResponse<Word> getWords(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String letters = query.toUpperCase();
        Page<Word> words = wordRepository.findByLettersContainingOrderByLettersAsc(letters, pageable);
        return new PagedResponse<>(words);
    }

    @Override
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }


}
