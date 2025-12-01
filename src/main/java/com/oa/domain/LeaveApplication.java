package com.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplication {
    private Long id;
    private Long applicationId;
    private Long leaveTypeId;
    private LocalDate startTime;
    private LocalDate endTime;
    private Integer totalDays;
    private String reason;
    private String status;
    private Long currentApproverId;
}
