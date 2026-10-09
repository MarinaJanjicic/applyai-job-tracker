package com.applyai.backend.service;


import com.applyai.backend.dto.jobapplication.CreateJobApplicationRequest;
import com.applyai.backend.dto.jobapplication.CreateJobApplicationResponse;
import com.applyai.backend.dto.jobapplication.UpdateJobApplicationRequest;
import com.applyai.backend.entity.ApplicationStatus;
import com.applyai.backend.entity.JobApplication;
import com.applyai.backend.entity.User;
import com.applyai.backend.exception.JobApplicationNotFoundException;
import com.applyai.backend.exception.UserNotFoundException;
import com.applyai.backend.repository.JobApplicationRepository;
import com.applyai.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    public CreateJobApplicationResponse create(CreateJobApplicationRequest dto, String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));

        JobApplication jobApplication=JobApplication.builder()
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .location(dto.getLocation())
                .jobUrl(dto.getJobUrl())
                .applicationDate(dto.getApplicationDate())
                .status(ApplicationStatus.APPLIED)
                .notes(dto.getNotes())
                .user(user)
                .build();

        JobApplication saved=jobApplicationRepository.save(jobApplication);

        return mapToResponse(saved);

    }

    public List<CreateJobApplicationResponse> getAll(String userEmail,ApplicationStatus status){
        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));
        List<JobApplication> jobApplications;
        if(status!=null){

            jobApplications=jobApplicationRepository.findAllByUserIdAndStatus(user.getId(),status);

        }else{
           jobApplications=jobApplicationRepository.findAllByUserId(user.getId());

        }
        return jobApplications.stream()
                .map(this::mapToResponse)
                .toList();

    }


    public CreateJobApplicationResponse getById(Long id, String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));

        JobApplication jobApplication=jobApplicationRepository.findByIdAndUserId(id,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Job application not found"));

        return mapToResponse(jobApplication);
    }

    public CreateJobApplicationResponse update(Long id, UpdateJobApplicationRequest dto,
                                               String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));

        JobApplication application=jobApplicationRepository.findByIdAndUserId(id,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Job application not found"));

        application.setCompanyName(dto.getCompanyName());
        application.setPosition(dto.getPosition());
        application.setJobUrl(dto.getJobUrl());
        application.setLocation(dto.getLocation());
        application.setApplicationDate(dto.getApplicationDate());
        application.setStatus(dto.getStatus());
        application.setNotes(dto.getNotes());

        JobApplication updated=jobApplicationRepository.save(application);

        return mapToResponse(updated);

    }
    public void delete(Long id, String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new RuntimeException("User not found"));

        JobApplication application=jobApplicationRepository.findByIdAndUserId(id,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Job application not found"));

        jobApplicationRepository.delete(application);
    }



    private CreateJobApplicationResponse mapToResponse(JobApplication jobApplication){
        return new CreateJobApplicationResponse(
                jobApplication.getId(),
                jobApplication.getCompanyName(),
                jobApplication.getPosition(),
                jobApplication.getJobUrl(),
                jobApplication.getLocation(),
                jobApplication.getApplicationDate(),
                jobApplication.getStatus(),
                jobApplication.getNotes()
        );
    }
}
