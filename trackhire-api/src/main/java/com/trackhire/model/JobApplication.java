package com.trackhire.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dateApplied;
}
