package com.applyai.backend.service;


import com.applyai.backend.dto.jobapplication.CreateJobApplicationRequest;
import com.applyai.backend.dto.jobapplication.CreateJobApplicationResponse;
import com.applyai.backend.entity.ApplicationStatus;
import com.applyai.backend.entity.JobApplication;
import com.applyai.backend.entity.User;
import com.applyai.backend.repository.JobApplicationRepository;
import com.applyai.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public CreateJobApplicationResponse create(CreateJobApplicationRequest dto, String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new RuntimeException("User not found"));

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

        return new CreateJobApplicationResponse(saved.getId(),
                saved.getCompanyName(),
                saved.getPosition(),
                saved.getJobUrl(),
                saved.getLocation(),
                saved.getApplicationDate(),
                saved.getStatus(),
                saved.getNotes());

    }
}
