package com.oa.mapper;

import com.oa.domain.LeaveApplication;
import com.oa.dto.LeaveTypesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMapper {
    List<LeaveTypesDTO> getAllLeaveTypes();

    void insert(LeaveApplication leaveApplication);

    List<LeaveApplication> getAllLeaveDetails();
}
