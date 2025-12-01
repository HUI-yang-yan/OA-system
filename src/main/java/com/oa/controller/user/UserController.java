package com.oa.controller.user;

import com.oa.domain.User;
import com.oa.result.Result;
import com.oa.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/add/user")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
