package com.oa.controller.workspace;

import com.oa.result.Result;
import com.oa.service.WorkSpaceService;
import com.oa.utils.BaseContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/workspace")
@Slf4j
public class WorkSpaceController {
    @Resource
    private WorkSpaceService workSpaceService;

    @GetMapping("/attendance")
    public Result getAttendance() {
        return workSpaceService.getAttendance();
    }

    @PostMapping("/sign/in")
    public Result signIn(@RequestParam LocalDateTime signInTime) {
        return workSpaceService.signIn(signInTime);
    }

    @GetMapping("/equipment/status")
    public Result equipmentStatus() {
        return workSpaceService.equipmentStatus();
    }

    @GetMapping("/meetingRoom")
    public Result meetingRoomStatus(){
        return workSpaceService.meetingRoomStatus();
    }

    @PostMapping("/sign/out")
    public Result SignOut(@RequestParam LocalDateTime signOutTime) throws UnknownHostException {
        return workSpaceService.signOut(signOutTime);
    }

    @GetMapping("/records/{id}")
    public Result getAttendanceRecordById(@PathVariable Long id){
        return workSpaceService.getAttendanceById(id);
    }

    @GetMapping("/records")
    public Result getAttendanceRecord(){
        return workSpaceService.getAttendanceById(BaseContext.getUserId());
    }
}
