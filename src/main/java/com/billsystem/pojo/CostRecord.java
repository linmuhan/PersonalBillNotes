package com.billsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CostRecord {

    private int monthCost;
    private int todayCost;
    private int surplusCost;
    private int dailyAvg;
    private int dailyUse;
    private int surplusDay;


}
