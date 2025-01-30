package com.esliceu.movies.repos;

import com.esliceu.movies.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person findByPersonName(String personName);

    Page<Person> findAll(Pageable pageable);
}
