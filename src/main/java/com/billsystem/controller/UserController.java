package com.billsystem.controller;

import com.billsystem.pojo.User;
import com.billsystem.service.UserService;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("insertUser")
    public String insertUser(@RequestBody User user){
        try {
            User bean = userService.queryUserByName(user.getName());
            if(bean != null){
                return "exist";
            }else{
                userService.insertUser(user);
                return "ok";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("validateUser")
    public Object validateUser(@RequestBody User user, HttpSession session){
        try{
            User bean = userService.queryUserByName(user.getName());
            if(bean == null){
                return "noExist";
            }else{
                if(bean.getPassword().equals(user.getPassword())){
                    session.setAttribute(user.getName(),user.getName());
                    return Result.success(bean.getId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

}
