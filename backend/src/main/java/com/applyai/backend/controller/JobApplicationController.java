package com.applyai.backend.controller;


import com.applyai.backend.dto.jobapplication.CreateJobApplicationRequest;
import com.applyai.backend.dto.jobapplication.CreateJobApplicationResponse;
import com.applyai.backend.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping
    public CreateJobApplicationResponse create(@RequestBody @Valid CreateJobApplicationRequest dto,
                                               @AuthenticationPrincipal String userEmail
    ){

        return jobApplicationService.create(dto,userEmail);
    }
}
