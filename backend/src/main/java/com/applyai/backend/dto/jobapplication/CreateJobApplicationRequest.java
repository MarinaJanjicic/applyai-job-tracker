package com.applyai.backend.dto.jobapplication;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobApplicationRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String position;

    private String jobUrl;

    private String location;

    @NotNull
    private LocalDate applicationDate;

    private String notes;
}
