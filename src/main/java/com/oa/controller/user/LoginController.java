package com.oa.controller.user;

import com.oa.dto.ForgetPasswordDTO;
import com.oa.dto.LoginDTO;
import com.oa.dto.ResetPasswordDTO;
import com.oa.result.Result;
import com.oa.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * @author admin
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("")
    public Result login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    /**
     * 忘记密码
     * @param forgetPasswordDTO
     * @return
     */
    @PostMapping("/forget/password")
    public Result forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO){
        return userService.forgetPassword(forgetPasswordDTO);
    }

    /**
     * 校验验证码
     * @param code
     * @param email
     * @param userName
     * @return
     */
    @GetMapping("/check/code")
    public Result checkCode(@RequestParam String code, @RequestParam String email,@RequestParam String userName){
        return userService.checkCode(code,email,userName);
    }

    /**
     * 重置密码
     * @param resetPasswordDTO
     * @return
     */
    @PostMapping("/reset/password")
    public Result resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return userService.resetPassword(resetPasswordDTO);
    }
}
