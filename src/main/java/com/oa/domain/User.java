package com.oa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String employeeId;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String phone;
    private Long departmentId;
    private Integer status;
    private String position;
    private Set<Role> roles = new HashSet<>();
}
