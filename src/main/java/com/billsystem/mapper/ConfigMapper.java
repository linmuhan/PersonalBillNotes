package com.billsystem.mapper;


import com.billsystem.pojo.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfigMapper {

    int addConfig(Config config);

    int updateConfig(Config config);

    Config queryConfig(int uid);

}
