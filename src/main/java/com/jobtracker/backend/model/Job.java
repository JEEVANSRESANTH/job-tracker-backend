package com.jobtracker.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    private String id;

    @NotBlank(message = "Company is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String company;

    @NotBlank(message = "Role is required")
    @Size(min = 3, max = 100, message = "Role must be between 3 and 100 characters")
    private String role;

    @NotBlank(message = "Status is required")
    private String status;

    // ✅ Default constructor required by JPA
    public Job() {
        this.id = UUID.randomUUID().toString();
    }

    // ✅ Getters & Setters

    public String getId() {
        return id;
    }

    // (Optional) setter only if you want to allow manual ID setting
    // public void setId(String id) {
    //     this.id = id;
    // }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}