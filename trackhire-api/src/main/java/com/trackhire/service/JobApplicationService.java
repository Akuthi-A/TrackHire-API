package com.trackhire.service;

import com.trackhire.model.JobApplication;
import com.trackhire.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

    public JobApplication findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
    }

    public List<JobApplication> findAll() {
        return repository.findAll();
    }


    public JobApplication updateJobApplication(
            Long id,
            JobApplication updatedJob) {

        JobApplication existing = findById(id);

        existing.setRole(updatedJob.getRole());
        existing.setStatus(updatedJob.getStatus());

        return repository.save(existing);
    }


    public void deleteJobApplication(Long id) {
        JobApplication jobToDelete = findById(id);

        repository.delete(jobToDelete);
    }

}