package com.example.hangman.services;

import com.example.hangman.dto.PagedResponse;
import com.example.hangman.dto.catalog.CreateWordRequest;
import com.example.hangman.dto.catalog.UpdateWordRequest;
import com.example.hangman.models.Word;

public interface ICatalogService {

    Word createWord(CreateWordRequest request);

    Word updateWord(UpdateWordRequest request);

    PagedResponse<Word> getWords(String query, int page, int size);

    void deleteWord(Long id);
}
