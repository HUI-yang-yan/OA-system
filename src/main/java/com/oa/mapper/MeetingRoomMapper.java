package com.oa.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingRoomMapper {
    Integer getAllMeetingRoomNum();
    Integer getValidMeetingRoomNum();
}
