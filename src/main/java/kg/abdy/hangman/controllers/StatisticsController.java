package kg.abdy.hangman.controllers;

import kg.abdy.hangman.dto.PagedResponse;
import kg.abdy.hangman.dto.statistics.SearchParams;
import kg.abdy.hangman.dto.statistics.StatisticsResponse;
import kg.abdy.hangman.services.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class StatisticsController {

    private final IStatisticsService statisticsService;

    @Autowired
    public StatisticsController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/my/statistics")
    public ResponseEntity<PagedResponse<StatisticsResponse>> getMyStatistics(
            SearchParams params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String orderBy,
            @RequestParam(required = false, defaultValue = "DESC") String direction) {

        PagedResponse<StatisticsResponse> response = statisticsService
                .getMyStatistics(params, page, size, orderBy, direction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<PagedResponse<StatisticsResponse>> getStatistics(
            SearchParams params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String orderBy,
            @RequestParam(required = false, defaultValue = "DESC") String direction) {

        PagedResponse<StatisticsResponse> response = statisticsService
                .getStatistics(params, page, size, orderBy, direction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
