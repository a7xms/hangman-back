package com.example.hangman.services;

import com.example.hangman.dto.PagedResponse;
import com.example.hangman.dto.statistics.SearchParams;
import com.example.hangman.dto.statistics.StatisticsResponse;


public interface IStatisticsService {
    PagedResponse<StatisticsResponse> getMyStatistics(
            SearchParams params, int page, int size, String orderBy, String direction);

    PagedResponse<StatisticsResponse> getStatistics(
            SearchParams params, int page, int size, String orderBy, String direction);
}
