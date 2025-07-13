package com.project.one.controller.dto.response;

import com.project.one.domain.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String nationalCode;
    private LocalDate birthDate;
}
