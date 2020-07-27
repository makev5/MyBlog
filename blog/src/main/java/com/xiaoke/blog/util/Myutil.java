package com.xiaoke.blog.util;

import com.xiaoke.blog.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Myutil {

    @Autowired
    BlogMapper blogMapper;

    /**
     * 获取某个时间段内所有日期
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDayBetweenDates(String begin, String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = new ArrayList<>();

        Date dBegin = null;
        try {
            dBegin = sdf.parse(begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dEnd = null;
        try {
            dEnd = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateList.add(sdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();

        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(sdf.format(calBegin.getTime()));
        }
        return dateList;
    }
}
