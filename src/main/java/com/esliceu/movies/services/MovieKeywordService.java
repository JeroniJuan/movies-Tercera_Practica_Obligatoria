package com.esliceu.movies.services;

import com.esliceu.movies.models.Keyword;
import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Keywords;
import com.esliceu.movies.models.Movie_KeywordsKey;
import com.esliceu.movies.repos.MovieKeywordRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieKeywordService {
    @Autowired
    MovieKeywordRepo movieKeywordsRepo;

    @Autowired
    MovieService movieService;

    @Autowired
    KeywordService keywordService;

    public List<Movie_Keywords> findByMovieId(int movieId) {
        return movieKeywordsRepo.findByMovieId(movieId);
    }

    public void updateMovieKeywords(int movieId, List<Integer> keywordIds) {
        Movie movie = movieService.findById(movieId);
        List<Movie_Keywords> currentKeywords = movieKeywordsRepo.findByMovieId(movieId);

        Set<Integer> newKeywordIds = new HashSet<>(keywordIds);

        for (Movie_Keywords mk : currentKeywords) {
            if (!newKeywordIds.contains(mk.getKeyword().getId())) {
                movieKeywordsRepo.delete(mk);
            }
        }

        for (int keywordId : keywordIds) {
            Keyword keyword = keywordService.findById(keywordId);

            boolean exists = currentKeywords.stream()
                    .anyMatch(mk -> mk.getKeyword().getId() == keywordId);
            if (!exists) {
                Movie_KeywordsKey key = new Movie_KeywordsKey(movie.getId(), keyword.getId());
                Movie_Keywords movieKeyword = new Movie_Keywords();
                movieKeyword.setId(key);
                movieKeyword.setMovie(movie);
                movieKeyword.setKeyword(keyword);
                movieKeywordsRepo.save(movieKeyword);
            }
        }
    }

    @Transactional
    public void deleteByMovieId(int id) {
        movieKeywordsRepo.deleteByMovieId(id);
    }
    @Transactional
    public void deleteByKeywordId(int id) {
        movieKeywordsRepo.deleteByKeywordId(id);
    }
}
