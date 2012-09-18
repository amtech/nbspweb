package com.cabletech.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * 日期Date工具类
 * 
 * @author wangt
 * 
 */
public class DateUtil {
	private static String defineDateFormat = "yyyy-MM-dd HH:mm:ss";// 缺省日期格式
	private static Logger log = Logger.getLogger(DateUtil.class);
	
	/**
	 * 取得系统当前时间,类型为Timestamp
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		java.util.Date d = new java.util.Date();
		Timestamp numTime = new Timestamp(d.getTime());
		return numTime;
	}

	/**
	 * 取得系统当前时间,类型为Timestamp
	 * 
	 * @return Timestamp
	 */
	public static String getNowDateString() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 获取今年
	 * 
	 * @return
	 */
	public static String getNowYearStr() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}

	/**
	 * 获取今月
	 * 
	 * @return
	 */
	public static String getNowMonthStr() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(date);
	}

	/**
	 * 取得系统的当前时间,类型为java.sql.Date
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date getNowDate() {
		java.util.Date d = new java.util.Date();
		return new java.sql.Date(d.getTime());
	}

	/**
	 * 从Timestamp类型转化为yyyy-MM-dd类型的字符串
	 * 
	 * @param date
	 *            Timestamp
	 * @param strDefault
	 *            String
	 * @return
	 */
	public static String TimestampToString(Timestamp date, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * 从Timestamp类型转化为yyyy-MM-dd类型的字符串,如果为null,侧放回""
	 * 
	 * @param date
	 *            Timestamp
	 * @return
	 */
	public static String TimestampToString(Timestamp date) {
		return TimestampToString(date, null);
	}

	/**
	 * 将日期格式转化成字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return
	 */
	public static String DateToTimeString(java.util.Date date) {
		String strTemp = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * 将日期格式转化成字符串
	 * 
	 * @param date
	 *            java.sql.Date
	 * @return
	 */
	public static String DateToString(java.sql.Date date) {
		return DateToString(date, "yyyy-MM-dd");
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd
	 * 
	 * @param date
	 *            java.sql.Date
	 * @param fmt
	 *            String
	 * @return
	 */
	public static String DateToString(java.sql.Date date, String fmt) {
		String strTemp = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(fmt);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * 将日期格式转化成字符串
	 * 
	 * @param date
	 *            java.util.Date
	 * @return
	 */
	public static String DateToString(java.util.Date date) {
		return DateToString(date, "yyyy-MM-dd");
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd
	 * 
	 * @param date
	 *            java.util.Date
	 * @param strFmt
	 *            String
	 * @return
	 */
	public static String DateToString(java.util.Date date, String strFmt) {
		String strTemp = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * 日期格式精确到毫秒级转换为string型
	 * 
	 * @param date
	 *            java.util.Date
	 * @param strFormat
	 *            注意这里的格式,yyyy-MM-dd HH:mm:ss
	 * @return 返回此格式yyyy-MM-dd HH:mm:ss字符串
	 */
	public static String DateToTimeString(java.util.Date date, String strFormat) {
		String strTemp = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * String转化为Timestamp类型
	 * 
	 * @param strDate
	 *            String
	 * @return
	 */
	public static Timestamp StringToTimestamp(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = formatter.parse(strDate);
				Timestamp numTime = new Timestamp(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else
			return null;
	}

	/**
	 * 将时间字符串转化为java.util.Date格式
	 * 
	 * @param strDate
	 *            String 时间字符串
	 * @param strFmt
	 *            String 时间字符串的格式
	 * @return Date
	 */
	public static java.util.Date Str2UtilDate(String strDate, String strFmt) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
				java.util.Date dt = formatter.parse(strDate);
				return dt;
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		} else
			return null;
	}

	/**
	 * 将指定日期转化成指定字符串
	 * 
	 * @param dt
	 *            java.util.Date
	 * @param strFmt
	 *            String
	 * @return
	 */
	public static String UtilDate2Str(java.util.Date dt, String strFmt) {
		String strDt = null;
		if (dt != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
				strDt = formatter.format(dt);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return strDt;
	}

	/**
	 * 获取指定格式的Sysdate字符串
	 * 
	 * @param strFmt
	 *            String
	 * @return
	 */
	public static String getNowDateString(String strFmt) {
		String strDt = null;
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
		strDt = formatter.format(dt);
		return strDt;
	}

	/**
	 * String转化为java.sql.date类型，
	 * 
	 * @param strDate
	 *            String
	 * @param strFmt
	 *            String
	 * @return
	 */
	public static java.sql.Date StringToDate(String strDate, String strFmt) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
				java.util.Date d = formatter.parse(strDate);
				java.sql.Date numTime = new java.sql.Date(d.getTime());
				return numTime;
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		} else
			return null;
	}

	/**
	 * String转化为java.util.date类型，
	 * 
	 * @param strDate
	 *            String
	 * @param strFmt
	 *            String
	 * @return
	 */
	public static java.util.Date StringToUtilDate(String strDate, String strFmt) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(strFmt);
				return formatter.parse(strDate);
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		} else
			return null;
	}

	/**
	 * String转化为java.sql.date类型，
	 * 
	 * @param strDate
	 *            String
	 * @return
	 */
	public static java.sql.Date StringToDate(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = formatter.parse(strDate);
				java.sql.Date numTime = new java.sql.Date(d.getTime());
				return numTime;
			} catch (Exception e) {
				log.error(e);
				return null;
			}
		} else
			return null;
	}

	/**
	 * 取得年月日
	 * 
	 * @param type
	 *            int
	 * @param dateStr
	 *            String
	 * @return String[]
	 */
	public static String[] parseStringForDate(int type, String dateStr) {
		String[] dateArr = new String[6];
		// type : 1, "2004/05/06" 2, "2004/05/06 11:22:33" , 3, "11:22:33"

		if (type == 1) {
			dateArr[0] = dateStr.substring(0, 4);
			dateArr[1] = dateStr.substring(5, 7);
			dateArr[2] = dateStr.substring(8, 10);

		} else {
			if (type == 2) {
				dateArr[0] = dateStr.substring(0, 4);
				dateArr[1] = dateStr.substring(5, 7);
				dateArr[2] = dateStr.substring(8, 10);

				dateArr[3] = dateStr.substring(11, 13);
				dateArr[4] = dateStr.substring(14, 16);
				dateArr[5] = dateStr.substring(17, 19);

			} else {
				if (type == 3) {
					dateArr[0] = dateStr.substring(0, 2);
					dateArr[1] = dateStr.substring(3, 5);
					dateArr[2] = dateStr.substring(6, 8);

				}
			}
		}

		return dateArr;

	}

	/**
	 * 得到当前年
	 * 
	 * @since 1.0
	 * @return 返回当前年(YYYY)
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到当前月
	 * 
	 * @since 1.0
	 * @return 返回当前月(1~12)
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取给定日期的月
	 * 
	 * @param date
	 *            Date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @since 1.0
	 * @return 返回当前日(1~31)
	 */
	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 将字符串按指定格式解析成日期对象
	 * 
	 * @since 1.1
	 * @param dateStr
	 *            需要进行转换的日期字符串
	 * @param pattern
	 *            日期字符串的格式
	 * @return "yyyy-MM-dd HH:mm:ss"形式的日期对象
	 */
	public static java.util.Date parseDate(String dateStr, String pattern) {
		SimpleDateFormat DATEFORMAT = new SimpleDateFormat(defineDateFormat);
		DATEFORMAT.applyPattern(pattern);
		java.util.Date ret = null;
		try {
			ret = DATEFORMAT.parse(dateStr);
		} catch (Exception e) {
			log.error(e);
		}
		DATEFORMAT.applyPattern(defineDateFormat);
		return ret;
	}

	/**
	 * 计算两日期间相差天数.
	 * 
	 * @since 1.1
	 * @param d1
	 *            日期字符串
	 * @param d2
	 *            日期字符串
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(String d1, String d2) {
		java.sql.Date dd1 = null;
		java.sql.Date dd2 = null;
		long result = -1l;
		try {
			dd1 = java.sql.Date.valueOf(d1);
			dd2 = java.sql.Date.valueOf(d2);
			result = signDaysBetweenTowDate(dd1, dd2);
		} catch (Exception ex) {
			result = -1;
		}
		return result;
	}

	/**
	 * 计算两日期间相差天数.
	 * 
	 * @since 1.1
	 * @param d1
	 *            开始日期 日期型
	 * @param d2
	 *            结束日期 日期型
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(java.sql.Date d1, java.sql.Date d2) {
		return (d1.getTime() - d2.getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * 获取季度日期
	 * 
	 * @param year
	 *            String
	 * @param season
	 *            int
	 * @param isstart
	 *            boolean
	 * @return
	 */
	public static String getSeasonTime(String year, int season, boolean isstart) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];
		int years_value = Integer.parseInt(year);
		int start_days = 1;
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = "";
		if (isstart) {
			seasonDate = year + "-" + start_month + "-" + start_days;
		} else {
			seasonDate = year + "-" + end_month + "-" + end_days;
		}
		return seasonDate;
	}

	/**
	 * 获取某月日期
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            int
	 * @param isstart
	 *            boolean
	 * @return
	 */
	public static String getMonthTime(String year, int month, boolean isstart) {
		int years_value = Integer.parseInt(year);
		int start_days = 1;
		int end_days = getLastDayOfMonth(years_value, month);
		String seasonDate = "";
		if (isstart) {
			seasonDate = year + "-" + month + "-" + start_days;
		} else {
			seasonDate = year + "-" + month + "-" + end_days;
		}
		return seasonDate;
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 判断当前日期是否在许可的日期范围之中
	 * 
	 * @param dateStr
	 *            String
	 * @param spaceDays
	 *            long
	 * @return
	 */
	public static boolean compare(String dateStr, long spaceDays) {
		// TODO Auto-generated method stub
		java.util.Date date = Str2UtilDate(dateStr, "yyyy-MM-dd");
		java.util.Date nowDate = new java.util.Date();
		long s = nowDate.getTime() - date.getTime();
		s = s / (24 * 60 * 60 * 1000);
		if (s <= spaceDays)
			return true;
		else
			return false;
	}
	/**
     * 获取本周时间段
     * @return HashMap<startTime,endTime>
     */
    public static HashMap<String,String> getWeekSlot(){
        HashMap<String,String> map = new HashMap<String,String>();
        Date nowdate= new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear());
        cal.set(Calendar.MONTH,getMonth());
        Date date=cal.getTime();
        // 减去离上周日有几天的天数
        cal.add(Calendar.DATE, -(cal.get(cal.DAY_OF_WEEK)-1));
        map.put("startTime", df.format(date));
        cal.add(Calendar.DATE, cal.DAY_OF_WEEK);
        date=cal.getTime();
        map.put("endTime", df.format(date));
        
        return map;
    }
    
    /**
     * 获取本月时间段
     * @return HashMap<startTime,endTime>
     */
    public static HashMap<String,String> getMonthSlot(){
        HashMap<String,String> map = new HashMap<String,String>();
        
        Date nowdate= new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal=Calendar.getInstance();

        cal.set(Calendar.YEAR, getYear());

        cal.set(Calendar.MONTH, getMonth()-1);

        cal.add(Calendar.DATE, -nowdate.getDate()+1);
        
        Date date=cal.getTime();

        map.put("startTime", df.format(date));

        int lastday=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        cal.add(Calendar.DATE, lastday-1);
        
        date=cal.getTime();
        map.put("endTime", df.format(date));
        
        return map;
    }
    
    /**
     * 获取本季度时间段
     * @return HashMap<startTime,endTime>
     */
    public static HashMap<String,String> getQuarterSlot(){
        HashMap<String,String> map = new HashMap<String,String>();
        int monthdate = getMonth();
        String dateb = "";
        String datee = "";
        if(monthdate==1||monthdate==2||monthdate==3)
        {
            dateb = getYear()+"-1-1";
            datee = getYear()+"-3-31";
        }
        if(monthdate==4||monthdate==5||monthdate==6)
        {
            dateb = getYear()+"-4-1";
            datee =getYear()+"-6-30";
        }
        if(monthdate==7||monthdate==8||monthdate==9)
        {
            dateb =getYear()+"-7-1";
            datee = getYear()+"-9-31";
        }
        if(monthdate==10||monthdate==11||monthdate==12)
        {
            dateb = getYear()+"-10-1";
            datee = getYear()+"-12-31";
        }
        
        map.put("startTime", dateb);
        map.put("endTime", datee);
        
        return map;
    }
    
    /**
     * 获取本年时间段
     * @return HashMap<startTime,endTime>
     */
    public static HashMap<String,String> getYearSlot(){
        HashMap<String,String> map = new HashMap<String,String>();
        String dateb = "";
        String datee = "";
        dateb = getYear()+"-1-1";
        datee = getYear()+"-12-31";
        map.put("startTime", dateb);
        map.put("endTime", datee);
        
        return map;
    }
    
    
    /**
     * 获取本年时间段
     * @return HashMap<startTime,endTime>
     */
    public static String getPreviousMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");    
        Calendar   calendar = Calendar.getInstance();   
        Date mydate = new Date();  
        try {  
            mydate = sdf.parse(getNowDateString("yyyy-MM"));  
        } catch (ParseException e) {  
        }  
        calendar.setTime(mydate);     
        calendar.add(Calendar.MONTH, -1);  
        return sdf.format(calendar.getTime())+"-01";
    }
}
