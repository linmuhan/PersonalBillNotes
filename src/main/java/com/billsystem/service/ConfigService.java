package com.billsystem.service;

import com.billsystem.mapper.ConfigMapper;
import com.billsystem.pojo.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    public void addConfig(Config config){
        configMapper.addConfig(config);
    }

    public void updateConfig(Config config){
        configMapper.updateConfig(config);
    }

    public Config queryConfig(int uid){
        return configMapper.queryConfig(uid);
    }

}
