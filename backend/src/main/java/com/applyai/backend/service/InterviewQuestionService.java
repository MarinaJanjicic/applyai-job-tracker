package com.applyai.backend.service;


import com.applyai.backend.dto.interview.InterviewQuestionsResponse;
import com.applyai.backend.entity.JobApplication;
import com.applyai.backend.entity.User;
import com.applyai.backend.exception.JobApplicationNotFoundException;
import com.applyai.backend.exception.UserNotFoundException;
import com.applyai.backend.repository.JobApplicationRepository;
import com.applyai.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewQuestionService {

    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final GroqAiService groqAiService;

    public InterviewQuestionsResponse generateQuestions(Long applicationId, String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));

        JobApplication application=jobApplicationRepository.findByIdAndUserId(applicationId,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Application not found"));

        List<String> questions = groqAiService.generateInterviewQuestions(application.getCompanyName(),application.getPosition());

        return new InterviewQuestionsResponse(application.getCompanyName(),application.getPosition(),questions);
    }
}
