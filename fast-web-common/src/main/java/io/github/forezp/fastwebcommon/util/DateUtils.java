package io.github.forezp.fastwebcommon.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final Log logger = LogFactory.getLog(DateUtils.class);

    public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
    public static final String DATE_CH_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_MONTH_FORMAT = "yyyy-MM";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DAYTIME_START = "00:00:00";
    public static final String DAYTIME_END = "23:59:59";

    private static final String[] FORMATS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss", "HH:mm", "HH:mm:ss", "yyyy-MM",
            "yyyy-MM-dd HH:mm:ss.S"};

    private DateUtils() {

    }


    public static Date convert(String str) {
        if (str != null && str.length() > 0) {
            if (str.length() > 10 && str.charAt(10) == 'T') {
                str = str.replace('T', ' ');// 去掉json-lib加的T字母
            }
            for (String format : FORMATS) {
                if (format.length() == str.length()) {
                    try {
                        Date date = new SimpleDateFormat(format).parse(str);
                        return date;
                    } catch (ParseException e) {
                        if (logger.isWarnEnabled()) {
                            logger.warn(e.getMessage());
                        }
                    }
                }
            }
        }
        return null;
    }


    public static Date convert(String str, String format) {
        if (!StringUtils.isEmpty(str)) {
            try {
                Date date = new SimpleDateFormat(format).parse(str);
                return date;
            } catch (ParseException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 将日期转换成format字符串
     *
     * @param date       例如: Sun Jun 10 09:18:00 CST 2018
     * @param dateFormat 例如: "yyyy-MM-dd HH:mm:ss"
     */
    public static String convert(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        if (null == dateFormat) {
            dateFormat = DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }


    public static String convert(Date date) {
        return convert(date, DATE_TIME_FORMAT);
    }


    public static Date concat(String ymd, String hm) {
        if (!StringUtils.isBlank(ymd) && !StringUtils.isBlank(hm)) {
            try {
                String dateString = ymd.concat(" ").concat(hm.substring(0, 2)).concat(":").concat(hm.substring(2, 4)).concat(":00");
                Date date = DateUtils.convert(dateString, DateUtils.DATE_TIME_FORMAT);
                return date;
            } catch (NullPointerException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn(e.getMessage());
                }
            }
        }
        return null;
    }


    public static String getDay(Date date) {
        return convert(date, DATE_SHORT_FORMAT);
    }


    public static String getChDate(Date date) {
        return convert(date, DATE_CH_FORMAT);
    }


    public static Date getStartDatetime(Date date) {
        String thisDate = convert(date, DATE_FORMAT);
        return convert(thisDate + " " + DAYTIME_START);
    }


    public static Date getEndDatetime(Date date) {
        String thisDate = convert(date, DATE_FORMAT);
        return convert(thisDate + " " + DAYTIME_END);
    }


    public static Date getEndDatetime(Date date, Integer diffDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String thisDate = dateFormat.format(date.getTime() + MILLIS_PER_DAY * diffDays);
        return convert(thisDate + " " + DAYTIME_END);
    }



    public static Date strToDate(String dateStr) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = formatDate.parse(dateStr);
        } catch (Exception e) {

        }
        return date;
    }


    public static Timestamp getLastEndDatetime(Date endTime) {
        Timestamp ts = new Timestamp(endTime.getTime());
        ts.setNanos(999999999);
        return ts;
    }


    public static Timestamp getEndTimeAdd(Date endTime) {
        Timestamp ts = new Timestamp(endTime.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ts);
        calendar.add(Calendar.MILLISECOND, 1000);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }


    public static Timestamp getDateAdd(Date date, int millisecond) {
        Timestamp ts = new Timestamp(date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ts);
        calendar.add(Calendar.MILLISECOND, millisecond);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }


    public static String addDay(Date date, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(new Date(date.getTime() + MILLIS_PER_DAY * day));
    }


    public static Date addDayToDate(Date date, int day) {
        return new Date(date.getTime() + MILLIS_PER_DAY * day);
    }

    public static Long getTimeDiff(String startTime, String endTime) {
        int months = 0;
        Long days = null;
        Date startDate = null;
        Date endDate = null;
        try {
            if (startTime.length() == 10 && endTime.length() == 10) {
                startDate = new SimpleDateFormat(DATE_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_FORMAT).parse(endTime);
                days = getTimeDiff(startDate, endDate);
            } else if (startTime.length() == 7 && endTime.length() == 7) {
                startDate = new SimpleDateFormat(DATE_MONTH_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_MONTH_FORMAT).parse(endTime);
                months = getMonthDiff(startDate, endDate);
                days = new Long((long) months);
            } else {
                startDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(endTime);
                days = getTimeDiff(startDate, endDate);
            }
        } catch (ParseException e) {
            if (logger.isWarnEnabled()) {
                logger.warn(e.getMessage());
            }
            days = null;
        }
        return days;
    }


    public static Long getTimeDiff(Date startTime, Date endTime) {
        Long days = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        days = (l_e - l_s) / MILLIS_PER_DAY;
        return days;
    }


    public static Long getMinuteDiff(Date startTime, Date endTime) {
        Long minutes = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        minutes = (l_e - l_s) / MILLIS_PER_MINUTE;
        return minutes;
    }


    public static Long getSecondDiff(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / MILLIS_PER_SECOND;
    }


    public static int getMonthDiff(Date startTime, Date endTime) {
        int months = 0;

        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(startTime);
        endCalendar.setTime(endTime);
        months = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return months;
    }


//    public static void main(String[] args) throws Exception {
//        String strType = "2018-06-20 12:00:00";
//        String format = "yyyy-MM-dd HH:mm:ss";
//        Date dateType = new Date();
//
//        Date date = new SimpleDateFormat(format).parse(strType);
//        String str = new SimpleDateFormat(format).format(dateType);
//        System.out.println("Date: " + date);
//        System.out.println("String: " + str);
//        Date newTime = new Date(date.getTime() + MILLIS_PER_DAY * 2);
//        System.out.println("newTime Date: " + newTime);
//        System.out.println("newTime String: " + new SimpleDateFormat(format).format(newTime));
//
//        System.out.println("相差天数: " + (newTime.getTime() - date.getTime()) / (1000l * 24 * 60 *60));
//        System.out.println("相差秒数: " + (newTime.getTime() - date.getTime()) / MILLIS_PER_SECOND);
//
//        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(date);
//
//        System.out.println("Calendar.getTimeInMillis() = " + calendar.getTimeInMillis());
//        System.out.println("Date.getTime() = " + dateType.getTime());
//        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
//
//        Date d = new Date(calendar.getTimeInMillis());
//        System.out.println(d.getTime());
//
//        System.out.println(calendar.getTimeInMillis() - dateType.getTime());
//        System.out.println((calendar.getTimeInMillis() - dateType.getTime()) / MILLIS_PER_SECOND);
//
//
//        Date currentTime = new Date();
//        System.out.println("当前时间: " + convert(currentTime));
//        Date newDate = getEndDatetime(currentTime, 1);
//        System.out.println("返回n天到23:59:59结束的日期: " + convert(newDate));
//
//        Date date1 = null;
//        try {
//            date1 = new SimpleDateFormat(DATE_TIME_FORMAT).parse("2018-06-10 09:18:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("String转换date1: " + date1);
//
//        System.out.println("返回该日期的最后一刻，精确到纳秒: " + getLastEndDatetime(currentTime));
//        System.out.println("返回该日期加1秒: " + getEndTimeAdd(currentTime));
//
//        System.out.println("相对当前日期，增加10天: " + addDay(currentTime, 10));
//        System.out.println("currentTime: " + currentTime);
//        System.out.println("date1: " + date1);
//        System.out.println("相差的天数: " + (currentTime.getTime() - date1.getTime()) / MILLIS_PER_DAY);
//
//    }

}

