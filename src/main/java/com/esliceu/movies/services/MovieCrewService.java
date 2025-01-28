package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Crew;
import com.esliceu.movies.repos.MovieCrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCrewService {
    @Autowired
    MovieCrewRepo movieCrewRepo;
    public int findMovieCrewByName(String directorName) {
        return movieCrewRepo.findByPersonPersonName(directorName).getFirst().getId().getPerson_id();
    }

    public List<Movie_Crew> findMovieCrewByPersonId(int personId){
        return movieCrewRepo.findByPersonId(personId);
    }

    public List<Movie_Crew> filterDirectors(List<Movie> movies) {
        List<Movie_Crew> directors = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getMovieCrews() != null) {
                List<Movie_Crew> movieDirectors = movie.getMovieCrews().stream()
                        .filter(crew -> "Director".equalsIgnoreCase(crew.getJob()))
                        .collect(Collectors.toList());

                directors.addAll(movieDirectors);
            }
        }

        return directors;
    }
}
