package com.oa.service;

import com.oa.result.Result;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

public interface WorkSpaceService {
    Result getAttendance();

    Result equipmentStatus();

    Result meetingRoomStatus();

    Result signIn(LocalDateTime signInTime);

    Result signOut(LocalDateTime signOutTime) throws UnknownHostException;

    Result getAttendanceById(Long id);
}
