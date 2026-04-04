package com.jobtracker.backend.controller;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {
    private final List<Map<String, Object>> jobs = new ArrayList<>();
    @GetMapping
    public List<Map<String,Object>> getAllJobs() {
        return jobs;
    }
    @PostMapping
    public ResponseEntity<Map<String,Object>> createJob(
            @RequestBody Map<String,Object> request
    ){
        Map<String,Object> job=Map.of(
                "id", UUID.randomUUID().toString(),
                "company", request.get("company"),
                "role", request.get("role"),
                "status", request.get("status")
        );
        jobs.add(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateJob(
            @PathVariable String id,
            @RequestBody Map<String,Object> request
    ){
        for(int i=0;i<jobs.size();i++){
            Map<String,Object> existingjob=jobs.get(i);
            if(existingjob.get("id").equals(id)){
                Map<String,Object> updatedJob=Map.of(
                        "id", id,
                        "company", request.get("company"),
                        "role", request.get("role"),
                        "status", request.get("status")
                );
                jobs.set(i,updatedJob);
                return ResponseEntity.ok(updatedJob);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        for (int i = 0; i < jobs.size(); i++) {
            Map<String, Object> existingjob = jobs.get(i);
            if (existingjob.get("id").equals(id)) {
                jobs.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
