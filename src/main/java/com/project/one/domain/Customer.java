package com.project.one.domain;

import com.project.one.domain.constant.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends Auditable implements Serializable {

    @Id
    @GeneratedValue
            (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3)
    @Column(nullable = false)
    private String firstName;

    @Size(min = 3)
    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Size(min = 3, max = 10)
    private String nationalCode;

    @Email
    private String email;


}
