package com.oa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageSelectWorkerDTO {
    private Integer pageNum;
    private Integer pageSize;
    private String username;
    private String employeeId;
    private Long departmentId;
    private Integer status;
    private String position;
    private LocalDateTime startTime;
}
