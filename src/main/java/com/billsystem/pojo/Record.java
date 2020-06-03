package com.billsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private int id;
    private int cid;
    private String spend;
    private String comment;
    private Date date;
    private int uid;
    private String categoryName;

}
