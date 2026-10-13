package com.applyai.backend.dto.interview;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewFeedbackResponse {

    private Integer score;
    private String feedback;
    private String suggestion;
}
