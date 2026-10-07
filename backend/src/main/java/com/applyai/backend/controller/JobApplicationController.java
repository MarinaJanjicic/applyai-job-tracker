package com.applyai.backend.controller;


import com.applyai.backend.dto.jobapplication.CreateJobApplicationRequest;
import com.applyai.backend.dto.jobapplication.CreateJobApplicationResponse;
import com.applyai.backend.dto.jobapplication.UpdateJobApplicationRequest;
import com.applyai.backend.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<CreateJobApplicationResponse> getAll(@AuthenticationPrincipal String userEmail){
        return jobApplicationService.getAll(userEmail);
    }

    @GetMapping("/{id}")
    public CreateJobApplicationResponse getById(@PathVariable Long id,
                                                @AuthenticationPrincipal String userEmail) {
        return jobApplicationService.getById(id, userEmail);
    }

    @PutMapping("/{id}")
    public CreateJobApplicationResponse update(@PathVariable Long id, @RequestBody
                                               @Valid UpdateJobApplicationRequest dto,
                                               @AuthenticationPrincipal String userEmail) {
        return jobApplicationService.update(id, dto, userEmail);
    }
}
