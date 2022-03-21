package com.project.one.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User extends AuditClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3)
    @Column(nullable = false)
    private String firstName;

    @Size(min = 3)
    @Column(nullable = false)
    private String lastName;

    @Enumerated
    private Gender gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Size(min = 3, max = 10)
    private String nationalCode;

    @Email
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String email;


}
