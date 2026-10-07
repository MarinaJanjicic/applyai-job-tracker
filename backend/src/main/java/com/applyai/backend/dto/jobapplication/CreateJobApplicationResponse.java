package com.applyai.backend.dto.jobapplication;


import com.applyai.backend.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobApplicationResponse {

    private Long id;
    private String companyName;
    private String position;
    private String jobUrl;
    private String location;
    private LocalDate applicationDate;
    private ApplicationStatus status;
    private String notes;
}
