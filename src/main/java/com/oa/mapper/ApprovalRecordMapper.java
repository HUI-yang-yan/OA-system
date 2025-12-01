package com.oa.mapper;

import com.oa.domain.ApprovalRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalRecordMapper {
    void insert(ApprovalRecord approvalRecord);
}
