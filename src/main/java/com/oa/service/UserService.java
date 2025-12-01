package com.oa.service;

import com.oa.domain.User;
import com.oa.dto.ForgetPasswordDTO;
import com.oa.dto.LoginDTO;
import com.oa.dto.ResetPasswordDTO;
import com.oa.result.Result;


public interface UserService {
    Result login(LoginDTO loginDTO);

    Result forgetPassword(ForgetPasswordDTO forgetPasswordDTO);

    Result resetPassword(ResetPasswordDTO resetPasswordDTO);

    Result checkCode(String code, String email, String userName);

    Result getUserById(Long id);

    Result addUser(User user);
}
