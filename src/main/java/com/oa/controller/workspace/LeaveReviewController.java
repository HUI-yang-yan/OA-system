package com.oa.controller.workspace;

import com.oa.dto.LeaveApplicationDTO;
import com.oa.result.Result;
import com.oa.service.LeaveReviewService;
import com.oa.utils.BaseContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave")
public class LeaveReviewController {
    @Resource
    private LeaveReviewService leaveReviewService;

    @GetMapping("/type")
    public Result getType(){
        return leaveReviewService.getType();
    }

    @PutMapping("/add/leave")
    public Result addLeave(@RequestBody LeaveApplicationDTO leaveApplicationDTO){
        return leaveReviewService.addLeave(leaveApplicationDTO);
    }

    @GetMapping("/get/approval")
    public Result getApproval(){
        return leaveReviewService.getApproval(BaseContext.getUserId());
    }
}
