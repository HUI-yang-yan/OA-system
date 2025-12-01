package com.oa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalRecord {
    private Long id;
    private Long applicationId;
    private Long approverId;
    private String approvalType;
    private String approvalResult;
    private String approvalComment;
    private LocalDate approvalTime;
}
