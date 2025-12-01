package com.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRecord {
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private LocalDate workDate;
    private Double totalHours;
    private String status; //考勤状态
    private String ipAddress;
    private String location;
}
