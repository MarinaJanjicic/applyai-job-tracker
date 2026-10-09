package com.applyai.backend.dto.interview;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQuestionsResponse {

    private String companyName;
    private String position;
    private List<String> questions;
}
