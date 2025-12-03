package com.oa.mapper;

import com.oa.domain.User;
import com.oa.dto.PageSelectWorkerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from oa_management_system.users where id = #{id}")
    User selectById(Long id);

    @Select("select * from oa_management_system.users where email = #{email}")
    User selectByEmail(String email);

    void update(User user);

    List<User> selectByIds(List<Integer> list);

    void updateBatch(List<User> userList);

    void deleteBatch(List<Integer> list);

    List<User> pageSelect(PageSelectWorkerDTO pageSelectWorkerDTO);

    Integer getUserNum();

    void insert(User user);

    Long getAdminId();
}
