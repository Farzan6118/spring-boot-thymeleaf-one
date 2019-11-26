package com.project.one.service;

import com.project.one.model.Person;
import com.project.one.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Cacheable("person")
    public Optional<Person> getPersonByIDNot(Long id) {
        return personRepository.findById(id);
    }
}
