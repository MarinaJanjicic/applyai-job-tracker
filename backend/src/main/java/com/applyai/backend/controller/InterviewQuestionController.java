package com.applyai.backend.controller;

import com.applyai.backend.dto.interview.InterviewAnswerRequest;
import com.applyai.backend.dto.interview.InterviewFeedbackResponse;
import com.applyai.backend.dto.interview.InterviewQuestionsResponse;
import com.applyai.backend.service.InterviewFeedbackService;
import com.applyai.backend.service.InterviewQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class InterviewQuestionController {

    private final InterviewQuestionService interviewQuestionService;
    private final InterviewFeedbackService interviewFeedbackService;

    @PostMapping("/{id}/interview-questions")
    public InterviewQuestionsResponse generateQuestions(@PathVariable Long id,
                                                        @AuthenticationPrincipal String userEmail){
        return interviewQuestionService.generateQuestions(id,userEmail);
    }

    @PostMapping("/{id}/interview-feedback")
    public InterviewFeedbackResponse evaluateResponse(
            @PathVariable Long id,
           @Valid @RequestBody InterviewAnswerRequest request,
            @AuthenticationPrincipal String userEmail
    ){
        return interviewFeedbackService.evaluateAnswer(id,userEmail,request);
    }
}
