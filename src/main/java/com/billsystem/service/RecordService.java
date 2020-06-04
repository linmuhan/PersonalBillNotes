package com.billsystem.service;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.billsystem.mapper.RecordMapper;
import com.billsystem.pojo.Record;
import com.billsystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordMapper recordMapper;

    public void add(Record record){
        recordMapper.addRecord(record);
    }

    public void updateRecord(Record record){
        recordMapper.updateRecord(record);
    }

    public void deleteRecord(int id){
        recordMapper.deleteRecord(id);
    }

    public Record queryRecord(int id){
        return recordMapper.queryRecord(id);
    }

    public List<Record> queryRecordList(int uid){
        List<Record> lists= recordMapper.queryRecordList(uid);
        for(int i = 0; i < lists.size(); i++){
            Record record = lists.get(i);
            java.util.Date date = record.getDate();
            record.setDate(DateUtil.util2sql(date));
        }
        return lists;
    }

    public List<Record> queryRecordToday(Record record){
        return recordMapper.queryRecordToday(record);
    }

    public List<Record> queryRecordThisMonth(Date start,Date end,int uid){
        return recordMapper.queryRecordThisMonth(start,end,uid);
    }

}
