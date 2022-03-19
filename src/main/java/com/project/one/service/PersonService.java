package com.project.one.service;

import com.project.one.model.Person;
import com.project.one.repository.PersonRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Cacheable("person")
    public Optional<Person> getPersonByIdNot(Long id) {
        return personRepository.findById(id);
    }
}
