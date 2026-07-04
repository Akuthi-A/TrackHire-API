package com.trackhire.service;

import com.trackhire.model.JobApplication;
import com.trackhire.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

}