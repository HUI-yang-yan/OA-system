package com.oa.service.impl;

import com.oa.context.RedisContext;
import com.oa.domain.User;
import com.oa.dto.ForgetPasswordDTO;
import com.oa.dto.LoginDTO;
import com.oa.dto.ResetPasswordDTO;
import com.oa.dto.UserDTO;
import com.oa.mapper.UserMapper;
import com.oa.result.Result;
import com.oa.service.UserService;
import com.oa.utils.BaseContext;
import com.oa.utils.EmailUtil;
import com.oa.utils.JwtUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private EmailUtil  emailUtil;
    @Resource
    private StringRedisTemplate redisTemplate;

    @Override
    public Result login(LoginDTO loginDTO) {
        Long id = BaseContext.getUserId();
        User user = userMapper.selectById(id);
        if(user == null){
            return Result.error("用户名错误！请检查是否输入正确！");
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return Result.error("密码错误！");
        }

        String token = jwtUtils.generateToken(user.getId());
        return Result.success(token);
    }

    @Override
    public Result forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
        User user = userMapper.selectByEmail(forgetPasswordDTO.getEmail());

        if(user == null){
            return Result.error("邮箱不存在！");
        }
        String code = emailUtil.sendVerificationCode(forgetPasswordDTO.getEmail());
        log.info("验证码为:{}",code);

        redisTemplate.opsForValue().set(RedisContext.EMAIL_KEY+user.getEmail(), code);

        return Result.success("发送验证码成功!");
    }


    @Override
    public Result checkCode(String code, String email, String userName) {
        User user = userMapper.selectByEmail(email);
        if(user == null){
            return Result.error("邮箱有误!");
        }
        String realCode = redisTemplate.opsForValue().get(RedisContext.EMAIL_KEY + user.getEmail());
        if(!code.equals(realCode)){
            return Result.error("验证码有误!");
        }
        return Result.success("验证成功!");
    }

    @Override
    public Result getUserById(Long id) {
        User user = userMapper.selectById(id);
        if(user == null){
            return Result.error("未知用户");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return Result.success(userDTO);
    }

    @Override
    public Result addUser(User user) {
        if (user.getUsername() == null) {
            user.setUsername(user.getRealName());
        }
        user.setStatus(0);
        user.setPassword("123456");
        userMapper.insert(user);
        return null;
    }

    @Override
    public Result resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userMapper.selectByEmail(resetPasswordDTO.getEmail());
        if(user == null){
            return Result.error("邮箱有误!");
        }
        if(user.getPassword().equals(resetPasswordDTO.getOldPassword())){
            return Result.error("原密码与新密码一致,无法修改!");
        }
        user.setPassword(resetPasswordDTO.getNewPassword());
        userMapper.update(user);
        return Result.success("修改成功,请重新登录!");
    }
}
