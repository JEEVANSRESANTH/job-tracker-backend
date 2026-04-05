package com.jobtracker.backend.service;

import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository repository;

    public JobServiceImpl(JobRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return repository.save(job);
    }

    @Override
    public Job updateJob(String id, Job updatedJob) {
        return repository.findById(id)
                .map(job -> {
                    job.setCompany(updatedJob.getCompany());
                    job.setRole(updatedJob.getRole());
                    job.setStatus(updatedJob.getStatus());
                    return repository.save(job);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteJob(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
