package com.utils.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日期工具类：
 * 拿到上一个月、拿到上一年……
 */
public final class DateUtil {

    private final static Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    private static final int FOUR = 4;
    private static final int TWO = 2;
    private static final int DAY_STR_LENGTH = 6;
    private static final int TEN = 10;
    private static final int FIFTEEN = 15;
    private static final int TWELVE = 12;

    private DateUtil(){

    }

    private static final String YYYYMM = "yyyyMM";

    /**
     * 拿到上一个月 param:yyyyMM
     * @param date
     * @return
     */
    public static  Integer getLastMonth(Integer date) {

        String stringDate=date.toString();
        Date fDate;
        String lastMonth = "";
        SimpleDateFormat sdf= DateFormatUtil.getSdf(YYYYMM);
        try {
            fDate =sdf.parse(stringDate);
            Calendar c = Calendar.getInstance();
            c.setTime(fDate);
            c.add(Calendar.MONTH, -1);
            lastMonth = sdf.format(c.getTime());
        } catch (ParseException e) {
           LOG.error("parse exception",e);
        }
        return Integer.valueOf(lastMonth);
    }


    /**
     * 拿到上一年  param:yyyyMM
     * @param date
     * @return
     */
    public static  Integer getLastYear(Integer date) {

        String stringDate=date.toString();
        Date fDate;
        String lastMonth = "";
        SimpleDateFormat sdf= DateFormatUtil.getSdf(YYYYMM);
        try {
            fDate =sdf.parse(stringDate);
            Calendar c = Calendar.getInstance();
            c.setTime(fDate);
            c.add(Calendar.YEAR, -1);
            lastMonth = sdf.format(c.getTime());
        } catch (ParseException e) {
            LOG.error("parse exception",e);
        }
        return Integer.valueOf(lastMonth);
    }

    public static Double calculationRadioDivide(Long nowNum ,Long lastNum){
        BigDecimal subtractNum = new BigDecimal(nowNum).subtract(new BigDecimal(lastNum));
        int r=new BigDecimal(lastNum).compareTo(BigDecimal.ZERO);
        if((r == 0)){
            return 0.0;
        }else{
            return (subtractNum.divide(new BigDecimal(lastNum),FOUR,BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    /**
     * 获取日期
     * @param integerDate
     * @return
     */
    public static Date integerToDateYYYYmm(Integer integerDate){
       return new Date(new Long(integerDate));
    }

    /**
     * 获得当前年
     * @return
     */
    public static String getNowYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    /**
     * 获得当前年月  如果前日期小于15 返回上上月，如果大于15显示上个月
     * @return
     */
    public static Integer getMonthByIfHalf(){
        SimpleDateFormat sdf = DateFormatUtil.getSdf(YYYYMM);
        Date monthByIfHalfRes = getMonthByIfHalfRestDate();
        return Integer.parseInt(sdf.format(monthByIfHalfRes));
    }

    public static Date getMonthByIfHalfRestDate(){
        Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        int nowDate = calendar.get(Calendar.DAY_OF_MONTH);
        if(nowDate<FIFTEEN){
            //月份减一
            calendar.add(Calendar.MONTH, -TWO);
            calendar.getTime();
            return calendar.getTime();
        }else{
            //月份减一
            calendar.add(Calendar.MONTH, -1);
            return calendar.getTime();
        }
    }

    /**
     * 获得当前时间
     * @param parturn
     * @return
     */
    public static String getNowTime(String parturn){
        SimpleDateFormat sdf = DateFormatUtil.getSdf(parturn);
        return sdf.format(new Date());

    }
    /**
     * 获得当前时间
     * @param
     * @return
     */
    public static String getNowTime(){
        String parturn = "yyyyMMdd hh:mm:ss";
        return getNowTime(parturn);
    }

    /**
     * 获得最后一天
     * @param mon
     * @return
     */
    public static String getLastMonth(String mon){
        String monthStr = mon.substring(FOUR);
        int month = Integer.parseInt(monthStr);
        String yearStr = mon.substring(0,FOUR);
        int year = Integer.parseInt(yearStr);
        int lastMonth = month-1;
        String lastMonthStr;
        if(lastMonth==0){
            int newYear = year - 1;
            lastMonthStr = newYear + "12";
        }else if(lastMonth<TEN){
            lastMonthStr = year+"0"+lastMonth;
        }else {
            lastMonthStr = year+""+lastMonth;
        }
        return lastMonthStr;
    }


    public static String getLastYearMonth(String mon){
        String monStr = mon.substring(0,FOUR);
        int year = Integer.parseInt(monStr);
        String lastYearMonth = year-1+mon.substring(FOUR);
        return lastYearMonth;
    }

    /*
     * 从年初到当前月份
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> getNowMonths(String mon){
        List<String> mons = new ArrayList<>();
        String year = mon.substring(0,FOUR);
        String monStr = mon.substring(FOUR);
        int month = Integer.parseInt(monStr);

        for(int i=1;i<=month;i++){
            String yearStr = null;
            if(i<TEN){
                yearStr = year+"0"+i;
                mons.add(yearStr);
            }else {
                yearStr = year+i;
                mons.add(yearStr);
            }
        }
        return mons;
    }

    public static List<String> getLastMonths(String mon){
        String lastMon = getLastMonth(mon);
        return getNowMonths(lastMon);
    }

    /**
     * 查找当前月份向前推12个月
     */
    public static List<String> getDateMonths(String mon){
        List<String> mons = new ArrayList<>();
        String yearStr = mon.substring(0,FOUR);
        String monStr = mon.substring(FOUR);
        int month = Integer.parseInt(monStr);
        int year = Integer.parseInt(yearStr)-1;
        for(int i=month+1;i<=TWELVE;i++){
            String monthStr = null;
            if(i<TEN) {
                monthStr = String.valueOf(year) + "0" + i;
            } else {
                monthStr = String.valueOf(year) + i;
            }

            mons.add(monthStr);
        }
        mons.addAll(getNowMonths(mon));
        //倒叙排行
        mons.sort((a,b)->(-1));
        return mons;

    }
    /**
     * 往前倒30天
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> getDateDays(String day) throws ParseException {
        List<String> days = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = format.parse(day);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(currentDate);
        String preDate = null;
        //把当前时间加上
        days.add(day);
        for(int i = 0;i<30;i++){
            calendar.add(Calendar.DATE, -1);
            preDate = format.format(calendar.getTime());
            days.add(preDate);
        }
        return days;
    }
}
