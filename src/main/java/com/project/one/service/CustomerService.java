package com.project.one.service;

import com.project.one.controller.dto.request.CustomerRequestDto;
import com.project.one.controller.dto.response.CustomerResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto findById(Integer id);

    List<CustomerResponseDto> findAll();

    void save(CustomerRequestDto requestDto);

    void update(Integer id, CustomerRequestDto requestDto);

    void delete(Integer id);

    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);
}
