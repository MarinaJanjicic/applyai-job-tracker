package com.applyai.backend.service;

import com.applyai.backend.dto.coverletter.CoverLetterRequest;
import com.applyai.backend.dto.coverletter.CoverLetterResponse;
import com.applyai.backend.entity.JobApplication;
import com.applyai.backend.entity.User;
import com.applyai.backend.exception.JobApplicationNotFoundException;
import com.applyai.backend.exception.UserNotFoundException;
import com.applyai.backend.repository.JobApplicationRepository;
import com.applyai.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverLetterService {

    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final GroqAiService groqAiService;

    public CoverLetterResponse generateCoverLetter(
                Long jobApplicationId,String userEmail, CoverLetterRequest request){
        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));
        JobApplication jobApplication=jobApplicationRepository.findByIdAndUserId(jobApplicationId,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Job application not found"));

        return groqAiService.generateCoverLetter(jobApplication.getCompanyName(),
                jobApplication.getPosition(), request.getAdditionalInfo());
    }
}
