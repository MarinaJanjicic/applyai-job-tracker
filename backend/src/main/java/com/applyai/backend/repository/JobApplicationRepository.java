package com.applyai.backend.repository;

import com.applyai.backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

    List<JobApplication> findAllByUserId(Long userId);
}
