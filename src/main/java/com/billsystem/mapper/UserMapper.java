package com.billsystem.mapper;

import com.billsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

   int insertUser(User user);

   int deleteUser(int id);

   int updateUser(User user);

   User queryUserById(int id);

   User queryUserByName(String name);

   List<User> queryUsers();
}
