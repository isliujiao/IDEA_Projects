package com.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 **/
public class DateUtil2 {
    private final static SimpleDateFormat DAY_DF = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat MONTH_DF = new SimpleDateFormat("yyyy-MM");
    private final static SimpleDateFormat TIME_DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 时间格式化
     * @author yp
     * @return String
     **/
    public static String formatDate(Date date, String pattern){
    	if(date==null){
    		return null;
		}
		SimpleDateFormat df = TIME_DF;
		if(StringUtils.isNoneBlank(pattern)){
			df = new SimpleDateFormat(pattern);
		}
		return df.format(date);
    }

    /**
     * 时间格式化
     * @author yp
     * @return String
     **/
    public static String formatObject(Object date, String pattern){
    	if(date==null){
    		return null;
		}
		SimpleDateFormat df = TIME_DF;
		if(StringUtils.isNoneBlank(pattern)){
			df = new SimpleDateFormat(pattern);
		}
		return df.format(date);
    }

    /**
     * 获取上月批次
     * @author yp
     * @return String
     **/
    public static String getLastBatch(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		calendar.add(Calendar.MONTH,-1);
		return MONTH_DF.format(calendar.getTime());
    }

    /**
     * 获取指定时间批次
     * @author yp
     * @return String
     **/
    public static String getBatch(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
		return MONTH_DF.format(calendar.getTime());
    }

    /**
     * 秒转分钟数
     * @author yp
     * @return String
     **/
    public static String secondToMinute(Integer seconds){
    	if(seconds==null){
    		return null;
		}
    	String res = "";
		int m = seconds / 60;
		int s = seconds % 60;
		if(m>0){
			res += m + "分钟";
		}
		res += s + "秒";
		return res;
    }

	/**
	 * 获取去年同期
	 * @author yp
	 * @return String
	 **/
	public static String getLastYearDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR,-1);
		return DAY_DF.format(calendar.getTime());
	}

	/**
	 * 获取上月同期
	 * @author yp
	 * @return String
	 **/
	public static String getLastMonthDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH,-1);
		return DAY_DF.format(calendar.getTime());
	}

	/**
	 * 获取上周同期
	 * @author yp
	 * @return String
	 **/
	public static String getLastWeekDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE,-7);
		return DAY_DF.format(calendar.getTime());
	}

	/**
	 * 获取当年第一天
	 * @author yp
	 * @return String
	 **/
	public static String getYearStart(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_YEAR,1);
		return DAY_DF.format(calendar.getTime());
	}

}
