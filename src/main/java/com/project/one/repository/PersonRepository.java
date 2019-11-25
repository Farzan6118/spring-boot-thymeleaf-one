package com.project.one.repository;

import com.project.one.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findById(long id);

    Iterable<Person> findByLastName(String lastName);

}
