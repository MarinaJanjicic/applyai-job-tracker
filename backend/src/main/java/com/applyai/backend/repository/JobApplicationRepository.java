package com.applyai.backend.repository;

import com.applyai.backend.entity.ApplicationStatus;
import com.applyai.backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

    List<JobApplication> findAllByUserId(Long userId);
    Optional<JobApplication> findByIdAndUserId(Long id, Long userId);
    List<JobApplication> findAllByUserIdAndStatus(Long userId, ApplicationStatus status);
    long countByUserIdAndStatus(Long userId, ApplicationStatus status);
    long countByUserId(Long userId);
}
