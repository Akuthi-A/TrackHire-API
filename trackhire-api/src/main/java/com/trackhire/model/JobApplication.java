package com.trackhire.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class JobApplication {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String companyName;

    @Setter
    @Getter
    private String role;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dateApplied;
}
