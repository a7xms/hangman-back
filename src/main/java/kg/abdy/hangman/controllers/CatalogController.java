package kg.abdy.hangman.controllers;


import kg.abdy.hangman.dto.PagedResponse;
import kg.abdy.hangman.dto.catalog.CreateWordRequest;
import kg.abdy.hangman.dto.catalog.UpdateWordRequest;
import kg.abdy.hangman.models.Word;
import kg.abdy.hangman.services.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ICatalogService catalogService;

    @Autowired
    public CatalogController(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/words")
    public ResponseEntity<PagedResponse<Word>> getWords(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        PagedResponse<Word> pagedResponse = catalogService.getWords(query, page, size);
        return new ResponseEntity<>(pagedResponse, HttpStatus.OK);
    }

    @PostMapping("/words")
    public ResponseEntity<Word> createWord(@RequestBody CreateWordRequest request) {
        Word word = catalogService.createWord(request);
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @PutMapping("/words")
    public ResponseEntity<Word> updateWord(@RequestBody UpdateWordRequest request) {
        Word word = catalogService.updateWord(request);
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @DeleteMapping("/words")
    public ResponseEntity<Object> deleteWord(@RequestParam Long id) {
        catalogService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
