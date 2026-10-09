package com.applyai.backend.service;


import com.applyai.backend.dto.dashboard.DashboardStatsResponse;
import com.applyai.backend.entity.ApplicationStatus;
import com.applyai.backend.entity.User;
import com.applyai.backend.exception.UserNotFoundException;
import com.applyai.backend.repository.JobApplicationRepository;
import com.applyai.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public DashboardStatsResponse getStats(String userEmail){

        User user=userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found"));

        long total=jobApplicationRepository.countByUserId(user.getId());
        long applied=jobApplicationRepository.countByUserIdAndStatus(user.getId(), ApplicationStatus.APPLIED);
        long interview=jobApplicationRepository.countByUserIdAndStatus(user.getId(), ApplicationStatus.INTERVIEW);
        long offer=jobApplicationRepository.countByUserIdAndStatus(user.getId(), ApplicationStatus.OFFER);
        long rejected=jobApplicationRepository.countByUserIdAndStatus(user.getId(), ApplicationStatus.REJECTED);
        long withdrawn=jobApplicationRepository.countByUserIdAndStatus(user.getId(), ApplicationStatus.WITHDRAWN);

        return new DashboardStatsResponse(total,applied,interview,offer,rejected,withdrawn);
    }
}
