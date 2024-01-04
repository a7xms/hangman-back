package kg.abdy.hangman.services;

import kg.abdy.hangman.dto.PagedResponse;
import kg.abdy.hangman.dto.statistics.SearchParams;
import kg.abdy.hangman.dto.statistics.StatisticsResponse;


public interface IStatisticsService {
    PagedResponse<StatisticsResponse> getMyStatistics(
            SearchParams params, int page, int size, String orderBy, String direction);

    PagedResponse<StatisticsResponse> getStatistics(
            SearchParams params, int page, int size, String orderBy, String direction);
}
