package com.billsystem.controller;

import com.billsystem.pojo.Record;
import com.billsystem.service.CategoryService;
import com.billsystem.service.RecordService;
import com.billsystem.util.DateUtil;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addRecord")
    public Object addRecord(@RequestBody Record record){
        try{
            Date date = record.getDate();
            record.setDate(DateUtil.util2sql(date));
            recordService.add(record);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

    @GetMapping("/listRecord/{uid}")
    public Object listRecord(@PathVariable int uid){
        try{
            List<Record> lists = recordService.queryRecordList(uid);
            Collections.reverse(lists);
            return Result.success(lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

    @GetMapping("/monthRecord/{uid}")
    public Object monthRecord(@PathVariable int uid){
        try{
            List<Integer> lists = new ArrayList<>();
            //获取12个月的数据
            for(int i = 1; i <= 12; i++){
                Date start = DateUtil.getFirstDayOfMonth(i);
                Date end = DateUtil.getLastDayOfMonth(i);
                List<Record> records = recordService.queryRecordThisMonth(DateUtil.util2sql(start),DateUtil.util2sql(end),uid);
                int sum = 0;
                for(Record r : records){
                    sum += Integer.parseInt(r.getSpend());
                }
                lists.add(sum);
            }
            return Result.success(lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }
}
