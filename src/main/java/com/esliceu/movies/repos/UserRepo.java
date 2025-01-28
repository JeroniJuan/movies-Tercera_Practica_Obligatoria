package com.esliceu.movies.repos;

import com.esliceu.movies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByMail(String mail);

    User findByUserName(String username);
}
