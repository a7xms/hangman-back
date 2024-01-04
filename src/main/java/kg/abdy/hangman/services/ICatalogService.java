package kg.abdy.hangman.services;

import kg.abdy.hangman.dto.PagedResponse;
import kg.abdy.hangman.dto.catalog.CreateWordRequest;
import kg.abdy.hangman.dto.catalog.UpdateWordRequest;
import kg.abdy.hangman.models.Word;

public interface ICatalogService {

    Word createWord(CreateWordRequest request);

    Word updateWord(UpdateWordRequest request);

    PagedResponse<Word> getWords(String query, int page, int size);

    void deleteWord(Long id);
}
