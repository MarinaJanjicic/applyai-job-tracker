package com.applyai.backend.controller;

import com.applyai.backend.dto.interview.InterviewQuestionsResponse;
import com.applyai.backend.service.InterviewQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class InterviewQuestionController {

    private final InterviewQuestionService interviewQuestionService;

    @PostMapping("/{id}/interview-questions")
    public InterviewQuestionsResponse generateQuestions(@PathVariable Long id,
                                                        @AuthenticationPrincipal String userEmail){
        return interviewQuestionService.generateQuestions(id,userEmail);
    }
}
