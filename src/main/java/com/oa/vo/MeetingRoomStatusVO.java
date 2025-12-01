package com.oa.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingRoomStatusVO {
    private Integer MeetingRoomNum;
    private Integer ValidMeetingRoomNum;
}
