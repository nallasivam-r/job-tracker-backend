package com.jobtracker.backend.controller;

import com.jobtracker.backend.dto.JobApplicationRequest;
import com.jobtracker.backend.entity.JobApplication;
import com.jobtracker.backend.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationService jobService;

    public JobApplicationController(JobApplicationService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobApplication> add(
            @RequestBody JobApplicationRequest req,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(jobService.addApplication(req, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAll(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(jobService.getUserApplications(userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> update(
            @PathVariable Long id,
            @RequestBody JobApplicationRequest req,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(jobService.updateApplication(id, req, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        jobService.deleteApplication(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}