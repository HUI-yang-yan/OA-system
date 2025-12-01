package com.oa.service.impl;

import com.oa.domain.ApprovalRecord;
import com.oa.domain.LeaveApplication;
import com.oa.domain.User;
import com.oa.dto.LeaveApplicationDTO;
import com.oa.dto.LeaveTypesDTO;
import com.oa.mapper.ApprovalRecordMapper;
import com.oa.mapper.LeaveMapper;
import com.oa.mapper.UserMapper;
import com.oa.result.Result;
import com.oa.service.LeaveReviewService;
import com.oa.utils.BaseContext;
import com.oa.vo.LeaveApplicationVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveReviewServiceImpl implements LeaveReviewService {
    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApprovalRecordMapper approvalRecordMapper;

    @Override
    public Result getType() {
        List<LeaveTypesDTO> list = leaveMapper.getAllLeaveTypes();
        return Result.success(list);
    }

    @Override
    public Result addLeave(LeaveApplicationDTO leaveApplicationDTO) {
        LeaveApplication leaveApplication = copy(leaveApplicationDTO);
        //insert的时候要返回id
        leaveMapper.insert(leaveApplication);
        ApprovalRecord approvalRecord = setApprovalRecord(leaveApplicationDTO, leaveApplication);
        approvalRecordMapper.insert(approvalRecord);
        return Result.success("上传请假信息成功,等待审批!");
    }

    @Override
    public Result getApproval(Long userId) {
        List<LeaveApplication> list = leaveMapper.getAllLeaveDetails();
        List<LeaveApplicationVO> list1 = new ArrayList<>();
        for (LeaveApplication leaveApplication : list) {
            LeaveApplicationVO leaveApplicationVO = new LeaveApplicationVO();
            User user = userMapper.selectById(leaveApplication.getCurrentApproverId());
            BeanUtils.copyProperties(leaveApplication, leaveApplicationVO);
            leaveApplicationVO.setUsername(user.getUsername());
            list1.add(leaveApplicationVO);
        }
        return Result.success(list1);
    }

    private static ApprovalRecord setApprovalRecord(LeaveApplicationDTO leaveApplicationDTO, LeaveApplication leaveApplication) {
        ApprovalRecord approvalRecord = new ApprovalRecord();
        approvalRecord.setApplicationId(leaveApplication.getId());
        approvalRecord.setApprovalResult(leaveApplicationDTO.getReason());
        approvalRecord.setApprovalTime(LocalDate.now());
        approvalRecord.setApproverId(BaseContext.getUserId());
        approvalRecord.setApprovalComment("");
        return approvalRecord;
    }

    private LeaveApplication copy(LeaveApplicationDTO leaveApplicationDTO) {
        LeaveApplication leaveApplication = new LeaveApplication();
        BeanUtils.copyProperties(leaveApplicationDTO, leaveApplication);
        leaveApplication.setApplicationId(BaseContext.getUserId());
        leaveApplication.setStatus("Pending");
        Period between = Period.between(leaveApplicationDTO.getStartTime(), leaveApplicationDTO.getEndTime());
        leaveApplication.setTotalDays(between.getDays());

        Long adminId = userMapper.getAdminId();

        leaveApplication.setCurrentApproverId(adminId);
        return leaveApplication;
    }
}
