package com.oa.mapper;

import com.oa.domain.AttendanceRecord;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    void insert(AttendanceRecord attendanceRecord);

    void updateById(AttendanceRecord attendanceRecord);

    List<AttendanceRecord> selectByUserId(Long id);

    AttendanceRecord selectByUserIdAndTime(Long userId, LocalDateTime now);
}
