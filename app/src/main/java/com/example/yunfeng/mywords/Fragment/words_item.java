package com.example.yunfeng.mywords.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yunfeng on 2018/6/14.
 */

public class words_item {
    public String words;
    public String meaning;
    Date date;
    Calendar calendar = Calendar.getInstance();
    public int imageids;
    //获取系统的日期
//年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH)+1;
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    //获取系统时间
//小时
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    //分钟
    int minute = calendar.get(Calendar.MINUTE);
    //秒
    int second = calendar.get(Calendar.SECOND);

    public words_item(String words, String meaning, int imageids) {
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
       // Date date = new Date(System.currentTimeMillis());//simpleDateFormat.format(date)
        this.words = words;
        this.meaning = meaning;
        this.imageids=imageids;
    }
    public int getDay(){
        return day;
    }
    public  int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public  String getWords(){
        return words;
    }
    public  String getMeaning(){
        return meaning;
    }
    public  int getImageid(){
        return imageids;
    }
}
