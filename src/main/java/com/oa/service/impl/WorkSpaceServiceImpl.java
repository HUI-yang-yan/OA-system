package com.oa.service.impl;

import com.oa.domain.AttendanceRecord;
import com.oa.mapper.*;
import com.oa.result.Result;
import com.oa.service.WorkSpaceService;
import com.oa.utils.BaseContext;
import com.oa.vo.AttendanceVO;
import com.oa.vo.EquipmentStatusVO;
import com.oa.vo.MeetingRoomStatusVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.oa.context.RedisContext.SIGN_IN_FIRST_KEY;
import static com.oa.context.RedisContext.SIGN_IN_KEY;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Resource
    private WorkSpaceMapper workSpaceMapper;

    @Resource
    private StringRedisTemplate redisTemplate;

    @Resource
    private AttendanceMapper attendanceMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private EquipmentMapper equipmentMapper;

    @Resource
    private MeetingRoomMapper meetingRoomMapper;

    @Override
    public Result getAttendance() {
        String times = redisTemplate.opsForValue().get(SIGN_IN_KEY + LocalDate.now());
        AttendanceVO  attendanceVO = new AttendanceVO();
        if (times != null) {
            attendanceVO.setAddendWorkerNum(Integer.parseInt(times));
        }
        Integer nums = userMapper.getUserNum();
        attendanceVO.setAllWorkerNum(nums);
        return Result.success(attendanceVO);
    }

    @Override
    public Result equipmentStatus() {
        EquipmentStatusVO equipmentStatusVO = new EquipmentStatusVO();
        equipmentStatusVO.setEquipmentNum(equipmentMapper.getAllEquipmentNum());
        equipmentStatusVO.setValidEquipmentNum(equipmentMapper.getValidEquipmentNum());
        return Result.success(equipmentStatusVO);
    }

    @Override
    public Result meetingRoomStatus() {
        MeetingRoomStatusVO meetingRoomStatusVO = new MeetingRoomStatusVO();
        meetingRoomStatusVO.setMeetingRoomNum(meetingRoomMapper.getAllMeetingRoomNum());
        meetingRoomStatusVO.setValidMeetingRoomNum(meetingRoomMapper.getValidMeetingRoomNum());
        return Result.success(meetingRoomStatusVO);
    }

    // 加锁
    @Override
    public Result signIn(LocalDateTime signInTime) {
        AttendanceRecord attendanceRecord = AttendanceRecord.builder()
                .checkInTime(signInTime)
                .userId(BaseContext.getUserId())
                .build();
        attendanceMapper.insert(attendanceRecord);
        String times = redisTemplate.opsForValue().get(SIGN_IN_KEY + LocalDate.now());
        if (times != null) {
            int num = Integer.parseInt(times);
            redisTemplate.opsForValue().set(SIGN_IN_KEY + LocalDate.now(), String.valueOf(num));

        } else {
            redisTemplate.opsForValue().set(SIGN_IN_KEY + LocalDate.now(), SIGN_IN_FIRST_KEY);
        }
        return Result.success("签到成功!");
    }

    @Override
    public Result signOut(LocalDateTime signOutTime) throws UnknownHostException {
        AttendanceRecord attendanceRecord = attendanceMapper.selectByUserIdAndTime(BaseContext.getUserId(),LocalDateTime.now());
        attendanceRecord.setCheckOutTime(signOutTime);
        attendanceRecord.setIpAddress(InetAddress.getLocalHost().getHostAddress());
        attendanceRecord.setWorkDate(LocalDate.now());

        // 获取签到时间
        LocalDateTime signInTime = attendanceRecord.getCheckInTime();
        if (signInTime == null) {
            return Result.error("今日未签到!");
        }

        // 计算工时
        Duration duration = Duration.between(signInTime, signOutTime);
        long hours = duration.toHours();

        if (hours < 0) {
            return Result.error("数据异常，请检查系统时间!");
        }

        String status;

        // 工作时长判断（是否早退）
        if (hours >= 8) {
            status = "normal";
        } else {
            status = "early";  // 早退
        }

        attendanceRecord.setStatus(status);
        attendanceMapper.updateById(attendanceRecord);

        return Result.success("签退成功：" + status);
    }

    @Override
    public Result getAttendanceById(Long id) {
        List<AttendanceRecord> list = attendanceMapper.selectByUserId(id);
        return Result.success(list);
    }
}
