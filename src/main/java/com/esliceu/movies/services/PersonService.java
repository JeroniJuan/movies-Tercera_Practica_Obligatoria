package com.esliceu.movies.services;

import com.esliceu.movies.models.Person;
import com.esliceu.movies.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Person findById(int id) {
        return personRepo.findById(id).orElse(null);
    }

    public Person save(Person person) {
        return personRepo.save(person);
    }

    public void deleteById(int id) {
        personRepo.deleteById(id);
    }

    public Person findPersonByName(String personName) {
        return personRepo.findByPersonName(personName);
    }
}
