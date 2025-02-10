package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Cast;
import com.esliceu.movies.models.Movie_CastKey;
import com.esliceu.movies.models.Person;
import com.esliceu.movies.repos.MovieCastRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieCastService {
    @Autowired
    MovieCastRepo movieCastRepo;

    @Autowired
    MovieService movieService;

    @Autowired
    PersonService personService;

    public List<Movie_Cast> findByMovieId(int movieId) {
        return movieCastRepo.findByMovieId(movieId);
    }

    @Transactional
    public void updateMovieCast(int movieId, List<String> castIds) {
        Movie movie = movieService.findById(movieId);

        List<Movie_Cast> currentCast = movieCastRepo.findByMovieId(movieId);
        Set<String> newCastSet = new HashSet<>(castIds);

        for (Movie_Cast mc : currentCast) {
            String currentKey = mc.getPerson().getPersonName() + ":" + mc.getCharacterName();
            if (!newCastSet.contains(currentKey)) {
                movieCastRepo.delete(mc);
            }
        }

        for (String castEntry : castIds) {
            String[] parts = castEntry.split(":");
            if (parts.length == 2) {
                String actorName = parts[0];
                String characterName = parts[1];

                Person person = personService.findPersonByName(actorName);
                if (person == null) {
                    person = new Person();
                    person.setPersonName(actorName);
                    personService.save(person);
                }

                boolean exists = currentCast.stream()
                        .anyMatch(mc -> mc.getPerson().getPersonName().equals(actorName)
                                && mc.getCharacterName().equals(characterName));

                if (!exists) {
                    Movie_Cast movieCast = new Movie_Cast();
                    movieCast.setMovie(movie);
                    movieCast.setPerson(person);
                    movieCast.setCharacterName(characterName);
                    save(movieCast);
                }
            }
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

    public void save(Movie_Cast movieCast) {
        movieCastRepo.save(movieCast);
    }
}
