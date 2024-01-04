package com.example.hangman.services.impl;

import com.example.hangman.dto.PagedResponse;
import com.example.hangman.dto.statistics.GuessDto;
import com.example.hangman.dto.statistics.SearchParams;
import com.example.hangman.dto.statistics.StatisticsResponse;
import com.example.hangman.models.Game;
import com.example.hangman.models.Guess;
import com.example.hangman.models.User;
import com.example.hangman.repositories.GameRepository;
import com.example.hangman.services.IStatisticsService;
import com.example.hangman.specifications.StatisticsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService implements IStatisticsService {

    private final UserService userService;
    private final GameRepository gameRepository;

    @Autowired
    public StatisticsService(UserService userService, GameRepository gameRepository) {
        this.userService = userService;
        this.gameRepository = gameRepository;
    }

    @Override
    public PagedResponse<StatisticsResponse> getMyStatistics(
            SearchParams params, int page, int size, String orderBy, String direction) {

        User user = userService.getCurrentUser();

        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Game> specification = StatisticsSpecification.getStatisticsSpecification(params, user);

        return getStatisticsPagedResponse(pageable, specification);

    }

    @Override
    public PagedResponse<StatisticsResponse> getStatistics(
            SearchParams params, int page, int size, String orderBy, String direction) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Game> specification = StatisticsSpecification.getStatisticsSpecification(params);

        return getStatisticsPagedResponse(pageable, specification);
    }

    private PagedResponse<StatisticsResponse> getStatisticsPagedResponse(Pageable pageable, Specification<Game> specification) {
        Page<Game> games = gameRepository.findAll(specification, pageable);
        List<StatisticsResponse> statistics = games.getContent()
                .stream()
                .map(this::statisticsResponse)
                .toList();

        return new PagedResponse<>(
                statistics,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages());
    }


    private StatisticsResponse statisticsResponse(Game game) {
        StatisticsResponse response = new StatisticsResponse();
        response.setWord(game.getActualWord());
        response.setStart(game.getCreatedAt());
        response.setFinish(game.getUpdatedAt());
        response.setResult(game.getResult());
        response.setGameId(game.getId());
        response.setFullName(game.getUser().getFirstName()
                .concat(" ").concat(game.getUser().getLastName()));
        response.setGuessedLetters(game.getGuesses().stream().map(this::toGuessDto).collect(Collectors.toList()));
        return response;
    }

    private GuessDto toGuessDto(Guess guess) {
        GuessDto guessDto = new GuessDto();
        guessDto.setLetter(guess.getLetter());
        guessDto.setCorrect(guess.getCorrect());
        return guessDto;
    }

}
