package com.applyai.backend.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsResponse {

    private long total;
    private long applied;
    private long interview;
    private long offer;
    private long rejected;
    private long withdrawn;
}
