package com.example.demo.convert;

import java.text.ParseException;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.example.demo.util.DateUtil;

/**
 * 日期格式转换
 * @author Administrator
 *
 */
public class DateConverter implements Converter<String, Date> {
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public Date convert(String dateStr) {
        try {
            return DateUtil.strToDate(dateStr,pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
