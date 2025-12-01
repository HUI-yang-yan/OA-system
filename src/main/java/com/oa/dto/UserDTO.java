package com.oa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String employeeId;
    private Long departmentId;
    private Integer status;
    private String position;
    private String realName;
    private String email;
    private String phone;
}
