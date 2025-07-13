package com.project.one.service.impl;

import com.project.one.controller.dto.request.CustomerRequestDto;
import com.project.one.controller.dto.response.CustomerResponseDto;
import com.project.one.domain.Customer;
import com.project.one.repository.CustomerRepository;
import com.project.one.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper modelMapper;

    @Cacheable("customer")
    @Override
    public CustomerResponseDto findById(Integer id) {
        Customer response = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
        return modelMapper.map(response, CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return repository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
                .toList();
    }

    @Transactional
    @Override
    public void save(CustomerRequestDto requestDto) {
        Customer customer = modelMapper.map(requestDto, Customer.class);
        repository.save(customer);
    }

    @Transactional
    @Override
    public void update(Integer id, CustomerRequestDto requestDto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));

        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customer.setGender(requestDto.getGender());
        customer.setBirthDate(requestDto.getBirthDate());
        customer.setNationalCode(requestDto.getNationalCode());

        repository.save(customer);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
