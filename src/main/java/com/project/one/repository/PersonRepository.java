package com.project.one.repository;

import com.project.one.model.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

}
