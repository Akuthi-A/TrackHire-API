package com.trackhire.controller;

import com.trackhire.model.JobApplication;
import com.trackhire.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}