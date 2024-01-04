package com.example.hangman.specifications;

import com.example.hangman.dto.statistics.SearchParams;
import com.example.hangman.enums.GameResult;
import com.example.hangman.models.Game;
import com.example.hangman.models.User;
import com.example.hangman.models.Word;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatisticsSpecification {

    public static Specification<Game> getStatisticsSpecification(SearchParams params) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isNotNull(root.get("result")));

            if(params.getWord() != null) {
                predicates.add(criteriaBuilder.like(root.get("actualWord"), formatLikeString(params.getWord().toUpperCase())));
            }
            if(params.getStart() != null) {
                Expression<LocalDate> createdAt = root.get("createdAt").as(LocalDate.class);
                predicates.add(criteriaBuilder.equal(createdAt, params.getStart()));
            }
            if(params.getFinish() != null) {
                Expression<LocalDate> updatedAt = root.get("updatedAt").as(LocalDate.class);
                predicates.add(criteriaBuilder.equal(updatedAt, params.getFinish()));
            }
            if(params.getResult() != null && !params.getResult().equals("All")) {
                predicates.add(criteriaBuilder.equal(root.get("result"), GameResult.valueOf(params.getResult())));
            }
            if(params.getFullName() != null){
                Join<Game, User> userJoin = root.join("user");
                Expression<String> firstName = criteriaBuilder.concat(userJoin.get("firstName"), " ");
                Expression<String> fullName = criteriaBuilder.lower(criteriaBuilder.concat(firstName, userJoin.get("lastName")));
                predicates.add(criteriaBuilder.like(fullName, formatLikeString(params.getFullName().toLowerCase())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Game> getStatisticsSpecification(SearchParams params, User currentUser) {
        return getStatisticsSpecification(params).and((root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(criteriaBuilder.equal(root.get("user"), currentUser));
        });
    }

    private static String formatLikeString(String param) {
        return "%" + param + "%";
    }


}
