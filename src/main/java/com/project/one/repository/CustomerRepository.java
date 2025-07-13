package com.project.one.repository;

import com.project.one.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Override
    List<Customer> findAll();

    boolean findByEmail(String email);

    boolean existsByEmail(String email);
}
