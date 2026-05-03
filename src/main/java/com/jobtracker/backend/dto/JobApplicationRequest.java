package com.jobtracker.backend.dto;

import java.time.LocalDate;

public class JobApplicationRequest {
    private String companyName;
    private String jobRole;
    private String status;
    private String jobUrl;
    private String notes;
    private LocalDate appliedDate;

    public JobApplicationRequest() {}

    public String getCompanyName() { return companyName; }
    public String getJobRole() { return jobRole; }
    public String getStatus() { return status; }
    public String getJobUrl() { return jobUrl; }
    public String getNotes() { return notes; }
    public LocalDate getAppliedDate() { return appliedDate; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }
    public void setStatus(String status) { this.status = status; }
    public void setJobUrl(String jobUrl) { this.jobUrl = jobUrl; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }
}