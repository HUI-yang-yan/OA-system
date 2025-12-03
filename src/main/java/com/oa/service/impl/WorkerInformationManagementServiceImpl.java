package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.domain.User;
import com.oa.dto.PageSelectWorkerDTO;
import com.oa.dto.UserDTO;
import com.oa.mapper.UserMapper;
import com.oa.result.PageResult;
import com.oa.result.Result;
import com.oa.service.WorkerInformationManagementService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerInformationManagementServiceImpl implements WorkerInformationManagementService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Result updateWorker(UserDTO userDTO) {
        User oriUser = userMapper.selectById(userDTO.getId());
        BeanUtils.copyProperties(userDTO, oriUser);
        userMapper.update(oriUser);
        return Result.success("更新成功!");
    }

    @Override
    public Result StartOrEnd(List<Integer> list, Integer status) {
        List<User> userList = userMapper.selectByIds(list);
        for(User user:userList){
            user.setStatus(status);
        }
        userMapper.updateBatch(userList);
        return Result.success("更新成功!");
    }

    @Override
    public Result deleteBatch(List<Integer> list) {
        userMapper.deleteBatch(list);
        return Result.success("删除成功!");
    }

//    @Override
//    public Result getWorkers() {
//        return null;
//    }

    @Override
    public Result pageSelectWorkers(PageSelectWorkerDTO pageSelectWorkerDTO) {
        PageHelper.startPage(pageSelectWorkerDTO.getPageNum(), pageSelectWorkerDTO.getPageSize());
        List<User> list = userMapper.pageSelect(pageSelectWorkerDTO);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(pageInfo.getTotal(), list);
        return Result.success(pageResult);
    }

    @Override
    public Result getWorkerById(Long id) {
        User user = userMapper.selectById(id);
        if(user == null){
            return Result.error("未知用户");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return Result.success(userDTO);
    }
}
