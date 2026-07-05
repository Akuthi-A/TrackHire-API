package com.trackhire.service;

import com.trackhire.exception.JobApplicationNotFoundException;
import com.trackhire.model.JobApplication;
import com.trackhire.model.Status;
import com.trackhire.repository.JobApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .orElseThrow(() ->
                        new JobApplicationNotFoundException(id));
    }

    public Page<JobApplication> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
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


    public Map<String, Long> getDashboard() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalApplications", repository.count());
        stats.put("applied", repository.countByStatus(Status.APPLIED));
        stats.put("interview", repository.countByStatus(Status.INTERVIEW));
        stats.put("offer", repository.countByStatus(Status.OFFER));
        stats.put("rejected", repository.countByStatus(Status.REJECTED));

        return stats;
    }

}

