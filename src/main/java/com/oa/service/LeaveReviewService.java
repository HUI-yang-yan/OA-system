package com.oa.service;

import com.oa.dto.LeaveApplicationDTO;
import com.oa.result.Result;

public interface LeaveReviewService {
    Result getType();

    Result addLeave(LeaveApplicationDTO leaveApplicationDTO);

    Result getApproval(Long userId);
}
