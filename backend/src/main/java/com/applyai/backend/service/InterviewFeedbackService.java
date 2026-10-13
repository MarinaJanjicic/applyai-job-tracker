package com.applyai.backend.service;


import com.applyai.backend.dto.interview.InterviewAnswerRequest;
import com.applyai.backend.dto.interview.InterviewFeedbackResponse;
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
public class InterviewFeedbackService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final GroqAiService groqAiService;

    public InterviewFeedbackResponse evaluateAnswer(
            Long applicationId, String userEmail, InterviewAnswerRequest request
    ){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));

        JobApplication jobApplication=jobApplicationRepository.findByIdAndUserId(applicationId,user.getId()).orElseThrow(()->new JobApplicationNotFoundException("Job application not found"));

        return groqAiService.evaluateAnswer(jobApplication.getCompanyName(),
                jobApplication.getPosition(),
                request.getQuestion(),request.getAnswer());
    }
}
