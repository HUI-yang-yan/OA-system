package com.oa.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String userName;
    private String oldPassword;
    private String newPassword;
}
