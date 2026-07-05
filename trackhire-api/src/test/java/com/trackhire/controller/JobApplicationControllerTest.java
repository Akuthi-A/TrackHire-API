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
import org.springframework.http.*;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobApplicationControllerTest {

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


    @Test
    void shouldReturn404WhenJobApplicationDoesNotExist() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/api/job-applications/999",
                        String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void shouldReturnAllJobApplications() {

        // Arrange
        JobApplication google = new JobApplication();
        google.setCompanyName("Google");
        google.setRole("Software Engineer");
        google.setStatus(Status.APPLIED);

        JobApplication microsoft = new JobApplication();
        microsoft.setCompanyName("Microsoft");
        microsoft.setRole("Backend Developer");
        microsoft.setStatus(Status.INTERVIEW);

        repository.save(google);
        repository.save(microsoft);

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/api/job-applications",
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        String json = response.getBody();

        assertNotNull(json);
        assertTrue(json.contains("Google"));
        assertTrue(json.contains("Microsoft"));
        assertTrue(json.contains("\"content\""));
    }


    @Test
    void shouldUpdateJobApplication() {

        // Arrange
        JobApplication job = new JobApplication();
        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        JobApplication saved = repository.save(job);

        JobApplication updated = new JobApplication();
        updated.setRole("Senior Backend Engineer");
        updated.setStatus(Status.INTERVIEW);

        // Act
        HttpEntity<JobApplication> request = new HttpEntity<>(updated);

        ResponseEntity<JobApplication> response =
                restTemplate.exchange(
                        "/api/job-applications/" + saved.getId(),
                        HttpMethod.PUT,
                        request,
                        JobApplication.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JobApplication result = response.getBody();

        assertNotNull(result);
        assertEquals("Google", result.getCompanyName());
        assertEquals("Senior Backend Engineer", result.getRole());
        assertEquals(Status.INTERVIEW, result.getStatus());
    }


    @Test
    void shouldDeleteJobApplication() {

        // Arrange
        JobApplication job = new JobApplication();
        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        JobApplication saved = repository.save(job);

        // Act
        ResponseEntity<Void> response =
                restTemplate.exchange(
                        "/api/job-applications/" + saved.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(repository.findById(saved.getId()).isPresent());
    }

}