package com.esliceu.movies.repos;

import com.esliceu.movies.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepo extends JpaRepository<Gender, Integer> {
}
