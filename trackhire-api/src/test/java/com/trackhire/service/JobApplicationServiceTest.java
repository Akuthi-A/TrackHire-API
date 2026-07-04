package com.trackhire.service;

import com.trackhire.model.JobApplication;
import com.trackhire.model.Status;
import com.trackhire.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    @Test
    void shouldCreateJobApplication() {

        JobApplication job = new JobApplication();
        job.setCompanyName("Google");
        job.setRole("Software Engineer");
        job.setStatus(Status.APPLIED);

        when(repository.save(job)).thenReturn(job);

        JobApplication saved = service.createJobApplication(job);

        assertEquals("Google", saved.getCompanyName());
        assertEquals(Status.APPLIED, saved.getStatus());

        verify(repository).save(job);
    }
}