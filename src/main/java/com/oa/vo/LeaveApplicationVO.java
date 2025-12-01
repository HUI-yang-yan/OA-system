package com.oa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplicationVO {
    private LocalDate startTime;
    private LocalDate endTime;
    private Integer totalDays;
    private String reason;
    private String status;
    private String username;
}
