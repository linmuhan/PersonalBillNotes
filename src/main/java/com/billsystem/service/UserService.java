package com.billsystem.service;

import com.billsystem.mapper.UserMapper;
import com.billsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }

    public void update(User user){
        userMapper.updateUser(user);
    }

    public User queryUserById(int id){
        return userMapper.queryUserById(id);
    }

    public List<User> queryUsers(){
        return userMapper.queryUsers();
    }

    public User queryUserByName(String name){
        return userMapper.queryUserByName(name);
    }
}
