package com.project.one.controller;

import com.project.one.controller.dto.request.CustomerRequestDto;
import com.project.one.controller.dto.response.CustomerResponseDto;
import com.project.one.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customer")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerRequestDto requestDto) {
        customerService.save(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody CustomerRequestDto requestDto) {
        customerService.update(id, requestDto);
        return ResponseEntity.ok().build();
    }
}
