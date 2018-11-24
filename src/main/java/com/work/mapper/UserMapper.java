package com.work.mapper;

import com.work.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    User selectByPrimaryKey(Integer userid);

    List<User> selectAll();

    User findPassword(String UserName);

    User findUserName(String UserName);

    int updateByPrimaryKey(User record);


}