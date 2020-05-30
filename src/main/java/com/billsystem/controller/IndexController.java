package com.billsystem.controller;
;
import com.billsystem.pojo.User;
import com.billsystem.service.UserService;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/welUser/{id}")
    public Object welUser(@PathVariable int id){
        User bean = userService.queryUserById(id);
        try {
            //将密码设置为空，防止被人审查到
            bean.setPassword("");
            return Result.success(bean);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

}
