package com.trackhire.controller;

import com.trackhire.model.JobApplication;
import com.trackhire.service.JobApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Page<JobApplication>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll(page, size));
    }


    @PutMapping("/{requestedId}")
    public ResponseEntity<JobApplication> updateJobApplication(
            @PathVariable Long requestedId,
            @RequestBody JobApplication jobApplication) {

        JobApplication updated =
                service.updateJobApplication(requestedId, jobApplication);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }


    @DeleteMapping("/{requestedId}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long requestedId) {

        service.deleteJobApplication(requestedId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Long>> getDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getDashboard());
    }
}