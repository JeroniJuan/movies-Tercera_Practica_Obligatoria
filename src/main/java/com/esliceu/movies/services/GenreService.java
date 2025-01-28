package com.esliceu.movies.services;

import com.esliceu.movies.models.Genre;
import com.esliceu.movies.repos.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepo genreRepo;

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Genre findById(int id) {
        return genreRepo.findById(id);
    }

    public Genre save(Genre genre) {
        return genreRepo.save(genre);
    }

    public void deleteById(int id) {
        genreRepo.deleteById(id);
    }
}
