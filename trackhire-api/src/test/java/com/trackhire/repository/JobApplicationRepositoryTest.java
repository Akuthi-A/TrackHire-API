package com.trackhire.repository;

import com.trackhire.model.JobApplication;
import com.trackhire.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository repository;

    @Test
    void shouldSaveJobApplication() {

        JobApplication job = new JobApplication();

        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        JobApplication saved = repository.save(job);

        assertNotNull(saved.getId());
        assertEquals("Google", saved.getCompanyName());
        assertEquals("Software Engineer", saved.getRole());
        assertEquals(Status.APPLIED, saved.getStatus());
    }
}