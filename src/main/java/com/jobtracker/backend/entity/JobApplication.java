package com.jobtracker.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private String status;

    private String jobUrl;
    private String notes;
    private LocalDate appliedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public JobApplication() {}

    public Long getId() { return id; }
    public String getCompanyName() { return companyName; }
    public String getJobRole() { return jobRole; }
    public String getStatus() { return status; }
    public String getJobUrl() { return jobUrl; }
    public String getNotes() { return notes; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }
    public void setStatus(String status) { this.status = status; }
    public void setJobUrl(String jobUrl) { this.jobUrl = jobUrl; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }
    public void setUser(User user) { this.user = user; }
}