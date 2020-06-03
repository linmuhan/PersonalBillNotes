package com.billsystem.controller;

import com.billsystem.pojo.Record;
import com.billsystem.service.CategoryService;
import com.billsystem.service.RecordService;
import com.billsystem.util.DateUtil;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
