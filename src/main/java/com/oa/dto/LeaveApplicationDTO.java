package com.oa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveApplicationDTO {
    private Long leaveTypeId;
    private LocalDate startTime;
    private LocalDate endTime;
    private String reason;
}
