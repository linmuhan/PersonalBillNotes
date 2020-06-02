package com.billsystem.mapper;

import com.billsystem.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
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

    List<Record> queryRecordList(Record record);

    List<Record> queryRecordToday(Record record);

    List<Record> queryRecordThisMonth(int uid,Date start,Date end);
}
