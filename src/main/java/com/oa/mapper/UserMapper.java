package com.oa.mapper;

import com.oa.domain.User;
import com.oa.dto.PageSelectWorkerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Long id);

    User selectByEmail(String email);

    void update(User user);

    List<User> selectByIds(List<Integer> list);

    void updateBatch(List<Integer> list);

    void deleteBatch(List<Integer> list);

    List<User> pageSelect(PageSelectWorkerDTO pageSelectWorkerDTO);

    Integer getUserNum();

    void insert(User user);

    Long getAdminId();

}
