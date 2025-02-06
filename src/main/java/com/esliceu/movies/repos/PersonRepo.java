package com.esliceu.movies.repos;

import com.esliceu.movies.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {
    Page<Person> findAll(Pageable pageable);

    List<Person> findByPersonNameContainingIgnoreCase(String personName);
}
