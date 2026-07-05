package com.trackhire.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackhire.model.JobApplication;
import com.trackhire.model.Status;
import com.trackhire.repository.JobApplicationRepository;
import com.trackhire.service.JobApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobApplicationControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JobApplicationRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateJobApplication() {

        JobApplication job = new JobApplication();
        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        ResponseEntity<JobApplication> response =
                restTemplate.postForEntity(
                        "/api/job-applications",
                        job,
                        JobApplication.class
                );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Google", response.getBody().getCompanyName());
    }


    @Test
    void shouldReturnJobApplicationById() {

        JobApplication job = new JobApplication();
        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        JobApplication saved = repository.save(job);


        ResponseEntity<JobApplication> response =
                restTemplate.getForEntity(
                        "/api/job-applications/" + saved.getId(),
                        JobApplication.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        JobApplication result = response.getBody();

        assertNotNull(result);
        assertEquals(saved.getId(), result.getId());
        assertEquals("Google", result.getCompanyName());
        assertEquals("Software Engineer", result.getRole());
        assertEquals(Status.APPLIED, result.getStatus());
    }
}