package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Cast;
import com.esliceu.movies.models.Movie_CastKey;
import com.esliceu.movies.models.Person;
import com.esliceu.movies.repos.MovieCastRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCastService {
    @Autowired
    MovieCastRepo movieCastRepo;

    public List<Movie_Cast> findByMovieId(int movieId) {
        return movieCastRepo.findByMovieId(movieId);
    }

    public void updateMovieCast(int movieId, List<Movie_Cast> movieCastList) {
        movieCastRepo.deleteByMovie_Id(movieId);
        for (Movie_Cast cast : movieCastList) {
            cast.setId(new Movie_CastKey(movieId, cast.getPerson().getId()));
            movieCastRepo.save(cast);
        }
    }

    public void updateMovieCast(int movieId, List<Integer> personIds, List<String> characterNames) {
        movieCastRepo.deleteByMovie_Id(movieId);

        for (int i = 0; i < personIds.size(); i++) {
            Movie_Cast movieCast = new Movie_Cast();
            movieCast.setMovie(new Movie(movieId));
            movieCast.setPerson(new Person(personIds.get(i)));
            movieCast.setCharacterName(characterNames.get(i));
            movieCastRepo.save(movieCast);
        }
    }


    public int getActorId(String actorName) {
        return movieCastRepo.findByPersonPersonName(actorName).getFirst().getId().getPerson_id();
    }

    public List<Movie_Cast> findAll() {
        return  movieCastRepo.findAll();
    }
    @Transactional
    public void deleteByMovieId(int id) {
        movieCastRepo.deleteByMovie_Id(id);
    }

    @Transactional
    public void deleteByGenderId(int id) {
        movieCastRepo.deleteByGenderId(id);
    }

    @Transactional
    public void deleteByPersonId(int id) {
        movieCastRepo.deleteByPersonId(id);
    }
}
