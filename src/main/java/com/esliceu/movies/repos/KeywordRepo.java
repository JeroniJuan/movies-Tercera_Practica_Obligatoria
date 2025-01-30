package com.esliceu.movies.repos;

import com.esliceu.movies.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepo extends JpaRepository<Keyword, Integer> {
    List<Keyword> findAll();

    Keyword findById(int id);

    Keyword findByKeywordName(String keywordName);
}
