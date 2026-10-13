package com.applyai.backend.dto.interview;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewAnswerRequest {

    @NotBlank
    private String question;

    @NotBlank
    private String answer;
}
