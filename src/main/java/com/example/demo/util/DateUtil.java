package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {

    private static SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static String dateParttern = "yyyy-MM-dd";
    
    /** 获取时间*/
    public static String dateToStr(Date date,String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
    /** 获取时间*/
    public static String dateToStr(Date date) {
        return dateToStr(date,dateParttern);
    }
    /** 获取时间 
     * @throws ParseException */
    public static Date strToDate(String dateStr,String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }
    /** 获取时间 */
    public static Date strToDate(String dateStr) throws ParseException {
        if(null == dateStr) {
            return null;
        }
        return new SimpleDateFormat(dateParttern).parse(dateStr);
    }
    /** 
     * day of week 获取指定日期所在周的星期几的日期信息 
     * @param date 目前的日期
     * @param dayOfWeek 1 - Monday ,7 - Sunday
     * @throws ParseException */
    public static Date dayOfWeek(Date date,int dayOfWeek) throws ParseException {
        // 日期格式转换
        String parttern = "yyyy-MM-dd";
        String dateStr = dateToStr(date,parttern);
        // 计算是周几
        LocalDate localDate = LocalDate.parse(dateStr);
        DayOfWeek day = localDate.getDayOfWeek();
        // 当前日期所在周的最后一天
        LocalDate sunday = localDate.plusDays(7 - day.getValue());
        // 当前周的具体的星期X
        LocalDate targetDay = sunday.minusDays(7-dayOfWeek);
        // Date 数据格式 返回
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parttern);
        return strToDate(targetDay.format(formatter),parttern);
    }
    
    /**
     * @param date
     * @return 1-7 （星期一 - 星期天）
     */
    public static int dayOfWeek(Date date) {
        // 日期格式转换
        String parttern = "yyyy-MM-dd";
        String dateStr = dateToStr(date,parttern);
        // 计算是周几
        LocalDate localDate = LocalDate.parse(dateStr);
        DayOfWeek day = localDate.getDayOfWeek();
        return day.getValue();
    }
}
