package com.applyai.backend.controller;

import com.applyai.backend.dto.dashboard.DashboardStatsResponse;
import com.applyai.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardStatsResponse getStats(@AuthenticationPrincipal String userEmail){
        return dashboardService.getStats(userEmail);
    }
}
