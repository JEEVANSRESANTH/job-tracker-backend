package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // ✅ GET all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // ✅ CREATE job
    @PostMapping
    public ResponseEntity<Job> createJob(
            @Valid @RequestBody Job job
    ) {
        Job saved = jobService.createJob(job);
        return ResponseEntity.status(201).body(saved);
    }

    // ✅ UPDATE job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable String id,
            @Valid @RequestBody Job job
    ) {
        Job updated = jobService.updateJob(id, job);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        boolean deleted = jobService.deleteJob(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}