package com.project.one.controller.dto.request;

import com.project.one.domain.constant.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDto {
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must be at most 50 characters")
        private String firstName;

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must be at most 50 characters")
        private String lastName;

        @NotNull(message = "Gender is required")
        private Gender gender;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "National code is required")
        @Size(min = 10, max = 10, message = "National code must be exactly 10 characters")
        private String nationalCode;

        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past")
        private LocalDate birthDate;
}