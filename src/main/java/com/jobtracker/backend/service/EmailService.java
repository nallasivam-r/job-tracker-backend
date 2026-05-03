package com.jobtracker.backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendApplicationConfirmation(String toEmail, String name,
                                             String company, String role) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Job Application Tracked: " + role + " at " + company);
        message.setText(
            "Hi " + name + ",\n\n" +
            "Your application for " + role + " at " + company +
            " has been successfully tracked!\n\n" +
            "Keep going — you've got this! 💪\n\n" +
            "— Job Tracker App"
        );
        mailSender.send(message);
    }

    public void sendStatusUpdateEmail(String toEmail, String name,
                                       String company, String newStatus) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Status Update: " + company + " → " + newStatus);
        message.setText(
            "Hi " + name + ",\n\n" +
            "Your application at " + company +
            " has been updated to: " + newStatus + "\n\n" +
            "Best of luck!\n— Job Tracker App"
        );
        mailSender.send(message);
    }
}