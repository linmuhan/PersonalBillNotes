package com.billsystem.mapper;

import com.billsystem.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@Mapper
public interface RecordMapper {

    int addRecord(Record record);

    int deleteRecord(int id);

    int updateRecord(Record record);

    Record queryRecord(int id);

    List<Record> queryRecordList(int uid);

    List<Record> queryRecordToday(Record record);

    List<Record> queryRecordThisMonth(@Param("start") Date start, @Param("end") Date end,@Param("uid") int uid);

    int queryCountOfCategory(@Param("cid") int cid,@Param("uid") int uid);
}
