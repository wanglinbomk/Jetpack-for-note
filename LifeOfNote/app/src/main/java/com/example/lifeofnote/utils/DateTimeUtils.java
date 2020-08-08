package com.example.lifeofnote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * author : joker
 * e-mail : 1105059963@qq.com
 * date   : 2019/3/219:27 AM
 * desc   :
 * version: 1.0
 * 日期工具类
 */

public class DateTimeUtils {

    private static SimpleDateFormat enLongDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static SimpleDateFormat enDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static SimpleDateFormat enNotYearDateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
    private static SimpleDateFormat enLongTimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private static SimpleDateFormat enShortTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private static SimpleDateFormat cnLongDateTimeFormat = new SimpleDateFormat("yyyy年MM月dd HH时mm分ss秒", Locale.getDefault());
    private static SimpleDateFormat cnDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
    private static SimpleDateFormat cnNotYearDateFormat = new SimpleDateFormat("MM月dd日", Locale.getDefault());
    private static SimpleDateFormat cnLongTimeFormat = new SimpleDateFormat("HH时mm分ss秒", Locale.getDefault());
    private static SimpleDateFormat cnShortTimeFormat = new SimpleDateFormat("HH时mm分", Locale.getDefault());
    private static SimpleDateFormat cnYearMonthFrormat = new SimpleDateFormat("yyyy年MM月", Locale.getDefault());
    private static SimpleDateFormat cnWeekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

    public static final int DAY = 0;
    public static final int MONTH = 1;
    public static final int YEAR = 2;


    // 获取当前日期时间，格式：2016-11-24 23:33:33
    public static String getEnLongDateTime() {
        return enLongDateTimeFormat.format(new Date());
    }

    // 获取当前日期，格式：2016-11-24
    public static String getEnDate() {
        return enDateFormat.format(new Date());
    }

    // 获取当前日期，格式：11-24
    public static String getEnNotYearDate() {
        return enNotYearDateFormat.format(new Date());
    }

    // 获取当前时间，格式：23:33:33
    public static String getEnLongTime() {
        return enLongTimeFormat.format(new Date());
    }

    // 获取当前时间，格式：23:33
    public static String getEnShortTime() {
        return enShortTimeFormat.format(new Date());
    }

    // 获取当前日期时间，格式：2016年11月24日 23时33分33秒
    public static String getCnLongDateTime() {
        return cnLongDateTimeFormat.format(new Date());
    }

    // 获取当前日期，格式：2016年11月24日
    public static String getCnDate() {
        return cnDateFormat.format(new Date());
    }

    //获取当前年份月份 2020年6月
    public static String getYMCnData(){
        return cnYearMonthFrormat.format(new Date());
    }

    // 获取当前日期，格式：11月24日
    public static String getCnNotYearDate() {
        return cnNotYearDateFormat.format(new Date());
    }

    // 获取当前时间，格式：23时33分33秒
    public static String getCnLongTime() {
        return cnLongTimeFormat.format(new Date());
    }

    // 获取当前时间，格式：23时33分
    public static String getCnShortTime() {
        return cnShortTimeFormat.format(new Date());
    }

    // 获取当前星期，格式：星期四
    public static String getCnWeek() {
        return cnWeekFormat.format(new Date());
    }

    // 根据范围类型获取友好日期时间
    public static String getFriendlyDateTime(int range, String datetime) {
        if (range == DAY) {
            return getFriendlyDay(datetime);
        } else if (range == MONTH) {
            return getFriendlyMonth(datetime);
        } else if (range == YEAR) {
            return getFriendlyYear(datetime);
        } else {
            return datetime;
        }
    }

    // 日期字符串转成友好月份（xx年后，明年，今年，去年，xx年前）
    public static String getFriendlyYear(String dateTimeStr) {
        int year = getNumberOfYear(new Date(), StringToDate(dateTimeStr));

        if (year < -1) {
            return Math.abs(year) + "年后";
        } else if (year == -1) {
            return "明年";
        } else if (year == 0) {
            return getFriendlyMonth(dateTimeStr);
        } else if (year == 1) {
            return "去年";
        } else if (year > 1) {
            return Math.abs(year) + "年前";
        }

        return dateTimeStr;
    }

    // 日期字符串转成友好月份（xx个月后，次月，当月，上个月，xx个月前）
    public static String getFriendlyMonth(String dateTimeStr) {
        int month = getNumberOfMonth(new Date(), StringToDate(dateTimeStr));
        if (month < -1) {
            return Math.abs(month) + "个月后";
        } else if (month == -1) {
            return "次月";
        } else if (month == 0) {
            return getFriendlyDay(dateTimeStr);
        } else if (month == 1) {
            return "上个月";
        } else if (month > 1) {
            return Math.abs(month) + "个月前";
        }

        return dateTimeStr;
    }

    // 日期字符串转换成友好日期（xx天后，明天，今天，昨天，xx天前）
    public static String getFriendlyDay(String dateTimeStr) {
        int days = getNumberOfDays(new Date(), StringToDate(dateTimeStr));
        if (days < -1) {
            return Math.abs(days) + "天后开抢";
        } else if (days == -1) {
            return "明天"+dateTimeStr.substring(11,16)+"开抢";
        } else if (days == 0) {
            return "今天";
        } else if (days == 1) {
            return "昨天";
        } else if (days > 1) {
            return Math.abs(days) + "天前";
        }
        return dateTimeStr;
    }

    // 日期字符串转换成友好时间（xx小时后，xx分钟后，刚刚，xx分钟前，xx小时前）
    public static String getFriendlyHourAndMinute(String dateTimeStr) {
        int minute = getNumberOfMinute(new Date(), StringToDate(dateTimeStr));
        int absMinute = Math.abs(minute);
        String suffix = minute > 0 ? "前" : "后";

        if (absMinute < 60) {
            if (absMinute == 0) {
                return "刚刚";
            } else {
                return absMinute + "分钟" + suffix;
            }
        } else {
            int hour = absMinute / 60;
            return hour + "小时" + suffix;
        }
    }

    //获取两个日期的年份差（忽略时分秒）
    public static int getNumberOfYear(Date date1, Date date2) {
        return getNumberOfMonth(date1, date2) / 12;
    }

    //获取两个日期的月数差（忽略时分秒）
    public static int getNumberOfMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalAccessError("string can not convert date");
        } else {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();

            calendar1.setTime(date1);
            calendar2.setTime(date2);

            int year1 = calendar1.get(Calendar.YEAR);
            int year2 = calendar2.get(Calendar.YEAR);

            int month1 = calendar1.get(Calendar.MONTH) + 1;
            int month2 = calendar2.get(Calendar.MONTH) + 1;

            if (year1 - year2 == 0) {
                return month1 - month2;
            } else {
                return (month1 - month2) + (year1 - year2) * 12;
            }
        }

    }

    // 获取两个日期的天数差（忽略时分秒）
    public static int getNumberOfDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalAccessError("string can not convert date");
        } else {
            int dateStamp1 = (int) (date1.getTime() / (24 * 60 * 60 * 1000));
            int dateStamp2 = (int) (date2.getTime() / (24 * 60 * 60 * 1000));

            return dateStamp1 - dateStamp2;
        }
    }

    // 获取两个时间的分钟差
    public static int getNumberOfMinute(Date time1, Date time2) {
        if (time1 == null || time2 == null) {
            throw new IllegalAccessError("string can not convert date");
        } else {
            int timeStamp1 = (int) (time1.getTime() / (60 * 1000));
            int timeStamp2 = (int) (time2.getTime() / (60 * 1000));

            return timeStamp1 - timeStamp2;
        }
    }

    // 日期字符串转日期对象
    public static Date StringToDate(String dateTimeStr) {
        try {
            return enLongDateTimeFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 日期字符串转日期对象（忽略时分秒）
    public static Date StringToDateIgnoreTime(String dateTimeStr) {
        try {
            return enDateFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 日期字符串转时间对象（忽然年月日）
    public static Date StringToTimeIgnoreDate(String dateTimeStr) {
        try {
            return enLongTimeFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 判断是否 2016-11-24日期格式
    public static boolean isDate(String dateStr) {
        try {
            return enDateFormat.parse(dateStr) != null;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 判断是否 23:33:33格式
    public static boolean isTime(String timeStr) {
        try {
            return enLongTimeFormat.parse(timeStr) != null;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //判断时间戳是否为昨天
    public static boolean isYesterday(long timestamp) {
        Calendar c = Calendar.getInstance();
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
        c.add(Calendar.DAY_OF_MONTH, -1);
        long firstOfDay = c.getTimeInMillis(); // 昨天最早时间
        c.setTimeInMillis(timestamp);
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND); // 指定时间戳当天最早时间
        return firstOfDay == c.getTimeInMillis();
    }



    private static void clearCalendar(Calendar c, int... fields) {
        for (int f : fields) {
            c.set(f, 0);
        }
    }

    public static String stampToDate(long s) {
        Date date = new Date(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String stampToDatess(long s) {
        Date date = new Date(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


    /**
     * 比较时间戳相差多少分钟
     */
    public static long comparisonTime(Long time) {
        return (System.currentTimeMillis() - time) / (1000 * 600);
    }

    public static long comparisonTimes(Long time, Long times) {
        return (time - times) / (1000 * 60);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDates(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateDay(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDateDayZero(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDatesss(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDatessss(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String getStart_time(int times) {
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        String time = year + "-" + month + "-" + day + "%20" + times + ":00";

        return time;
    }

    public static String getEndTime(String times) {
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时

        String time = year + "-" + month + "-" + day + " " + times + ":00";

        return time;
    }

    public static Long getSixMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.MONTH, -6);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    public static Long getThressMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.MONTH, -3);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     * @param date 指定日期
     * @return
     */
    public static Long getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 指定日期
     * @return
     */
    public static Long getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * @Author:whf
     * @param:
     * @Description: 获得“今天”零点时间戳 获得2点的加上2个小时的毫秒数就行
     * @Date:2018/4/12 0012
     */
    public static Long getTodayZeroPointTimestamps(){
        Long currentTimestamps= System.currentTimeMillis();
        Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
        return currentTimestamps-(currentTimestamps+60*60*8*1000)%oneDayTimestamps;
    }

    //获取本周开始时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取当天的开始时间
    public static java.util.Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    //获取当天的结束时间
    public static java.util.Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

}