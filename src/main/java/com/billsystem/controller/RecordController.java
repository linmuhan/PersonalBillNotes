package com.billsystem.controller;

import com.billsystem.pojo.Config;
import com.billsystem.pojo.CostRecord;
import com.billsystem.pojo.Record;
import com.billsystem.service.CategoryService;
import com.billsystem.service.ConfigService;
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
    @Autowired
    private ConfigService configService;

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

    @GetMapping("/costChart/{uid}")
    public Object costChart(@PathVariable int uid){
        try{
            //定义一个用来返回最后数据的实体类容器
            CostRecord bean = new CostRecord();
            //获取本月的第一天和最后一天
            Date start = DateUtil.monthBegin();
            Date end = DateUtil.monthEnd();
            //获取本月的账单
            List<Record> records = recordService.queryRecordThisMonth(DateUtil.util2sql(start),DateUtil.util2sql(end),uid);
            //获取一个月的预算
            Config config = configService.queryConfig(uid);
            int totalMoney = Integer.parseInt(config.getValue());
            //获取本月已经消费多少了
            int monthCost = 0;
            for(int i = 0; i < records.size(); i++){
                monthCost += Integer.parseInt(records.get(i).getSpend());
            }
            bean.setMonthCost(monthCost);
            //获取本月还剩多少
            int surplusCost = totalMoney - monthCost;
            bean.setSurplusCost(surplusCost);
            //获取今日消费的记录
            Record select = new Record();
            select.setDate(DateUtil.util2sql(DateUtil.today()));
            select.setUid(uid);
            List<Record> todayRecords = recordService.queryRecordToday(select);
            //计算今日消费的数额
            int todayCost = 0;
            for(int i = 0; i < todayRecords.size(); i++){
                todayCost += Integer.parseInt(todayRecords.get(i).getSpend());
            }
            bean.setTodayCost(todayCost);
            //获取这个月还剩多少天
            int surplusDay = DateUtil.thisMonthLeftDay();
            bean.setSurplusDay(surplusDay);
            //计算这个月过去这些天里每天的平均消费
            int leaveDay = DateUtil.thisMonthTotalDay()-DateUtil.thisMonthLeftDay();
            int dailyAvg = monthCost / leaveDay;
            bean.setDailyAvg(dailyAvg);
            //计算接下来日均可用
            int dailyUse = surplusCost / surplusDay;
            bean.setDailyUse(dailyUse);
            return Result.success(bean);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }
}
