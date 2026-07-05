package com.trackhire.controller;

import com.trackhire.model.JobApplication;
import com.trackhire.service.JobApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JobApplication> createJobApplication(
            @RequestBody JobApplication jobApplication) {

        JobApplication saved = service.createJobApplication(jobApplication);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }


    @GetMapping("/{requestedId}")
    public ResponseEntity<JobApplication> findById(@PathVariable Long requestedId) {
        JobApplication job = service.findById(requestedId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(job);
    }


    @GetMapping
    public ResponseEntity<List<JobApplication>> findAll() {
        List<JobApplication> jobs = service.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jobs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(
            @PathVariable Long id,
            @RequestBody JobApplication jobApplication) {

        JobApplication updated =
                service.updateJobApplication(id, jobApplication);

        return ResponseEntity.ok(updated);
    }
}