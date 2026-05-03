package com.jobtracker.backend.service;

import com.jobtracker.backend.dto.JobApplicationRequest;
import com.jobtracker.backend.entity.*;
import com.jobtracker.backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    public JobApplicationService(JobApplicationRepository jobRepo,
                                  UserRepository userRepo,
                                  EmailService emailService) {
        this.jobRepo = jobRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    private User getUser(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public JobApplication addApplication(JobApplicationRequest req, String email) {
        User user = getUser(email);
        JobApplication app = new JobApplication();
        app.setCompanyName(req.getCompanyName());
        app.setJobRole(req.getJobRole());
        app.setStatus(req.getStatus());
        app.setJobUrl(req.getJobUrl());
        app.setNotes(req.getNotes());
        app.setAppliedDate(req.getAppliedDate());
        app.setUser(user);
        JobApplication saved = jobRepo.save(app);
        emailService.sendApplicationConfirmation(
                user.getEmail(), user.getName(),
                req.getCompanyName(), req.getJobRole());
        return saved;
    }

    public List<JobApplication> getUserApplications(String email) {
        User user = getUser(email);
        return jobRepo.findByUserId(user.getId());
    }

    public JobApplication updateApplication(Long id, JobApplicationRequest req, String email) {
        User user = getUser(email);
        JobApplication app = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        if (!app.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        String oldStatus = app.getStatus();
        app.setCompanyName(req.getCompanyName());
        app.setJobRole(req.getJobRole());
        app.setStatus(req.getStatus());
        app.setJobUrl(req.getJobUrl());
        app.setNotes(req.getNotes());
        app.setAppliedDate(req.getAppliedDate());
        JobApplication updated = jobRepo.save(app);
        if (!oldStatus.equals(req.getStatus())) {
            emailService.sendStatusUpdateEmail(
                    user.getEmail(), user.getName(),
                    req.getCompanyName(), req.getStatus());
        }
        return updated;
    }

    public void deleteApplication(Long id, String email) {
        User user = getUser(email);
        JobApplication app = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        if (!app.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        jobRepo.delete(app);
    }
}