package com.billsystem.controller;


import com.billsystem.pojo.Config;
import com.billsystem.service.ConfigService;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @PostMapping("/setConfig")
    public Object setConfig(@RequestBody Config config){
        try{
            int uid = config.getUid();
            Config flag = configService.queryConfig(uid);
            //如果为空就进行添加，如果不为空就进行更新
            if(flag != null){
                configService.updateConfig(config);
                return Result.success();
            }else{
                configService.addConfig(config);
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

}
