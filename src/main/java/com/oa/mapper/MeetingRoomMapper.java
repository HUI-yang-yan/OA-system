package com.oa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MeetingRoomMapper {

    @Select("select * from oa_management_system.meeting_rooms")
    Integer getAllMeetingRoomNum();

    @Select("select * from oa_management_system.meeting_rooms where status = 'available'")
    Integer getValidMeetingRoomNum();
}
