package com.trackhire.repository;

import com.trackhire.model.JobApplication;
import com.trackhire.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    void delete(JobApplication jobToDelete);

    Page<JobApplication> findByStatus(
            Status status,
            Pageable pageable);

    Page<JobApplication> findByCompanyNameContainingIgnoreCase(
            String company,
            Pageable pageable);

    Long countByStatus(Status status);
}