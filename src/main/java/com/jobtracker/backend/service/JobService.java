package com.jobtracker.backend.service;

import com.jobtracker.backend.model.Job;

import java.util.List;

public interface JobService {

    List<Job> getAllJobs();

    Job createJob(Job job);

    Job updateJob(String id, Job job);

    boolean deleteJob(String id);
}