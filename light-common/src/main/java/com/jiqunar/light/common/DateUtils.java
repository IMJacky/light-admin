package com.jiqunar.light.common;

import com.jiqunar.light.model.common.DateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DateFormat.DEFAULT_DATE_FORMAT));
    }

    public static String getTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DateFormat.DEFAULT_TIME_FORMAT));
    }

    public static String getDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DateFormat.DEFAULT_DATE_TIME_FORMAT));
    }

    public static LocalDateTime getDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DateFormat.DEFAULT_DATE_TIME_FORMAT));
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static String getDayOfTheWeek(LocalDate date) {
        String[][] strArray = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"}, {"THURSDAY", "四"}, {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};
        String k = String.valueOf(date.getDayOfWeek());
        //获取行数
        for (int i = 0; i < strArray.length; i++) {
            if (k.equals(strArray[i][0])) {
                k = strArray[i][1];
                break;
            }
        }
        return "星期" + k;
    }
}
