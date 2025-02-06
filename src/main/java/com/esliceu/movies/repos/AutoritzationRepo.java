package com.esliceu.movies.repos;


import com.esliceu.movies.models.Autoritzation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoritzationRepo extends JpaRepository<Autoritzation, Integer> {

    List<Autoritzation> findByState(Autoritzation.State state);
}

