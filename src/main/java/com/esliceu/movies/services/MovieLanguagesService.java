package com.esliceu.movies.services;

import com.esliceu.movies.models.*;
import com.esliceu.movies.repos.MovieLanguagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieLanguagesService {
    @Autowired
    private MovieLanguagesRepo movieLanguagesRepo;

    @Autowired
    LanguageService languageService;

    @Autowired
    LanguageRoleService languageRoleService;

    @Autowired
    MovieService movieService;

    public List<Movie_Languages> findByMovieId(int movieId) {
        return movieLanguagesRepo.findByMovieId(movieId);
    }

    public void updateMovieLanguages(int movieId, List<Integer> languageIds, List<Integer> languageRoleIds) {
        Movie movie = movieService.findById(movieId);
        List<Movie_Languages> currentLanguages = movieLanguagesRepo.findByMovieId(movieId);

        Set<Integer> newLanguageIds = new HashSet<>(languageIds);

        for (Movie_Languages ml : currentLanguages) {
            if (!newLanguageIds.contains(ml.getLanguage().getId())) {
                movieLanguagesRepo.delete(ml);
            }
        }

        for (int i = 0; i < languageIds.size(); i++) {
            int languageId = languageIds.get(i);
            int roleId = languageRoleIds.get(i);

            Language language = languageService.findById(languageId);
            Language_Role role = languageRoleService.findById(roleId);

            boolean exists = currentLanguages.stream()
                    .anyMatch(ml -> ml.getLanguage().getId() == languageId);

            if (!exists) {
                Movie_LanguagesKey key = new Movie_LanguagesKey(movie.getId(), language.getId(), role.getId());
                Movie_Languages movieLanguage = new Movie_Languages();
                movieLanguage.setId(key);
                movieLanguage.setMovie(movie);
                movieLanguage.setLanguage(language);
                movieLanguage.setLanguageRole(role);
                movieLanguagesRepo.save(movieLanguage);
            }
        }
    }
}
