package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Keywords;
import com.esliceu.movies.models.Movie_KeywordsKey;
import com.esliceu.movies.repos.MovieKeywordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieKeywordService {
    @Autowired
    MovieKeywordRepo movieKeywordsRepo;

    public List<Movie_Keywords> findByMovieId(int movieId) {
        return movieKeywordsRepo.findByMovieId(movieId);
    }

    public void updateMovieKeywords(int movieId, List<Integer> keywordIds) {
        movieKeywordsRepo.deleteByMovieId(movieId);
        for (Integer keywordId : keywordIds) {
            Movie_Keywords mk = new Movie_Keywords(new Movie_KeywordsKey(movieId, keywordId));
            movieKeywordsRepo.save(mk);
        }
    }

}
