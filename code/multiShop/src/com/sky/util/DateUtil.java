package com.sky.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 日期工具类
 * @author Sky James
 *
 */
public class DateUtil {
	
	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	/**
	 * 默认时间字符串格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将日期字符串转换成日期类对象
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date convertStr2Date(String date) {
		Date destDate = null;
		
		String fromFmt = null;
		
		try {
			if(date.matches("\\d{14}")) {
				fromFmt = "yyyyMMddHHmmss";
			} else if(date.matches("\\d{9,14}")) {
				fromFmt = "yyyyMdHms";
			} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yyyy/M/d H:m:s";
			} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{2}")) {
				fromFmt = "yyyy/M/d H:mm";
			} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yyyy-M-d H:m";
			} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yyyy-M-d H:m:s";
			} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yyyy-M-d HH:mm:ss";
			} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yyyy-M-d HH:mm";
			} else if(date.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yyyy-MM-dd HH:mm:ss";
			} else if(date.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yyyy-MM-dd HH:mm";
			} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yyyy/M/d HH:mm:ss";
			} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yyyy/M/d HH:mm";
			} else if(date.matches("\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yyyy/MM/dd HH:mm:ss";
			} else if(date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
				fromFmt = "yyyy/MM/dd HH:mm";
			} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yy/M/d H:m:s";
			} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{2}")) {
				fromFmt = "yy/M/d H:mm";
			} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yy-M-d H:m";
			} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				fromFmt = "yy-M-d H:m:s";
			} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yy-M-d HH:mm:ss";
			} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yy-M-d HH:mm";
			} else if(date.matches("\\d{2}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yy-MM-dd HH:mm:ss";
			} else if(date.matches("\\d{2}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yy-MM-dd HH:mm";
			} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yy/M/d HH:mm:ss";
			} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yy/M/d HH:mm";
			} else if(date.matches("\\d{2}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
				fromFmt = "yy/MM/dd HH:mm:ss";
			} else if(date.matches("\\d{2}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}")) {
				fromFmt = "yy/MM/dd HH:mm";
			} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				fromFmt = "yyyy/M/d";
			} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				fromFmt = "yyyy-M-d";
			} else if(date.matches("\\d{4}-\\d{2}-\\d{2}")) {
				fromFmt = "yyyy-MM-dd";
			}
			
			if(fromFmt == null) {
				logger.warn("没有找到匹配的日期格式");
			} else {
				destDate = new SimpleDateFormat(fromFmt).parse(date);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		
		return destDate;
	}
	
	/**
	 * 将日期字符串转换成毫秒
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static long convertStr2MilliTime(String date) {
		return convertStr2Date(date).getTime();
	}
	
	/**
	 * 将日期字符串转换指定格式的日期字符串
	 * @param date 日期字符串
	 * @param fromFmt 原日期格式
	 * @param toFmt	待转换的日期格式
	 * @return 
	 * @throws Exception
	 */
	public static String convertDateStr(String date, String fromFmt, String toFmt) throws Exception {
		SimpleDateFormat fromSdf = new SimpleDateFormat(fromFmt);
		SimpleDateFormat toSdf = new SimpleDateFormat(toFmt);
		
		Date srcDate = null;
		String fmtDate = null;
		try {
			srcDate = fromSdf.parse(date);
		} catch (Exception e) {
			logger.error("将日期[" + date + "]格式化为[" + fromFmt + "]失败");
		}
		try {
			fmtDate = toSdf.format(srcDate);
		} catch (Exception e) {
			logger.error("将日期[" + date + "]格式化为[" + toFmt + "]失败");
		}
		
		return fmtDate;
	}
	
	/**
	 * 将日期字符串转换指定格式的日期字符串
	 * @param date
	 * @param toFmt
	 * @return
	 * @throws Exception
	 */
	public static String convertDateStr(String date, String toFmt) throws Exception {
		String fromFmt = null;
		
		if(date.matches("\\d{14}")) {
			fromFmt = "yyyyMMddHHmmss";
		} else if(date.matches("\\d{9,14}")) {
			fromFmt = "yyyyMdHms";
		} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yyyy/M/d H:m:s";
		} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{2}")) {
			fromFmt = "yyyy/M/d H:mm";
		} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yyyy-M-d H:m";
		} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yyyy-M-d H:m:s";
		} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yyyy-M-d HH:mm:ss";
		} else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yyyy-M-d HH:mm";
		} else if(date.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yyyy-MM-dd HH:mm:ss";
		} else if(date.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yyyy-MM-dd HH:mm";
		} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yyyy/M/d HH:mm:ss";
		} else if(date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yyyy/M/d HH:mm";
		} else if(date.matches("\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yyyy/MM/dd HH:mm:ss";
		} else if(date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
			fromFmt = "yyyy/MM/dd HH:mm";
		} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yy/M/d H:m:s";
		} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{2}")) {
			fromFmt = "yy/M/d H:mm";
		} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yy-M-d H:m";
		} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fromFmt = "yy-M-d H:m:s";
		} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yy-M-d HH:mm:ss";
		} else if(date.matches("\\d{2}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yy-M-d HH:mm";
		} else if(date.matches("\\d{2}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yy-MM-dd HH:mm:ss";
		} else if(date.matches("\\d{2}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yy-MM-dd HH:mm";
		} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yy/M/d HH:mm:ss";
		} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yy/M/d HH:mm";
		} else if(date.matches("\\d{2}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}")) {
			fromFmt = "yy/MM/dd HH:mm:ss";
		} else if(date.matches("\\d{2}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}")) {
			fromFmt = "yy/MM/dd HH:mm";
		} else if(date.matches("\\d{2}/\\d{1,2}/\\d{1,2}")) {
			fromFmt = "yyyy/M/d";
		}
		
		return convertDateStr(date, fromFmt, toFmt);
	}
	
	/**
	 * 将毫秒级的long时间，转换成指定格式的字符串
	 * @param time
	 * @param toFmt
	 * @return
	 * @throws Exception
	 */
	public static String convertDateStr(long milliTime, String toFmt) throws Exception {
		SimpleDateFormat toSdf = new SimpleDateFormat(toFmt);
		return toSdf.format(new Date(milliTime));
	}
	
	/**
	 * 获取主页上显示的当前日期
	 * @return 日期	格式：yyyy年MM月dd日	星期D
	 */
	public static String getIndexDate() {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		
		String day = null;
		
		switch(c.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY : day = "日"; break;
			case Calendar.MONDAY : day = "一"; break;
			case Calendar.TUESDAY : day = "二"; break;
			case Calendar.WEDNESDAY : day = "三"; break;
			case Calendar.THURSDAY : day = "四"; break;
			case Calendar.FRIDAY : day = "五"; break;
			case Calendar.SATURDAY : day = "六"; break;
		}
		
		return new SimpleDateFormat("yyyy年MM月dd日").format(date) + "\t星期" + day;
	}
	
	/**
	 * 获取指定日期范围内的日期的字符串列表，不包括星期六星期天
	 * 日期格式:yyyyMMdd
	 * 
	 * @param startday 开始日期
	 * @param endday 结束日期
	 * @return
	 */
	public static List<String> getDateStrByStartAndEnd(String startday, String endday) {
		List<String> dateStrList = new LinkedList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			Calendar startDay = Calendar.getInstance();
			startDay.setTime(sdf.parse(startday));
			
			Calendar endDay = Calendar.getInstance();
			endDay.setTime(sdf.parse(endday));
			
			while(startDay.compareTo(endDay) <= 0) {
				//排除星期六星期天
				int dayOfWeek = startDay.get(Calendar.DAY_OF_WEEK);
				boolean isRestDay = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
				
				if(!isRestDay) {
					dateStrList.add(sdf.format(startDay.getTime()));
				}
				
				startDay.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return dateStrList;
	}
	
	/**
	 * 获取当天0点0分0秒的时间
	 * @return
	 */
	public static Date getInstanceDate() {
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		return c.getTime();
	}
	
	public static void main(String[] args) throws Exception {
//		Calendar c = Calendar.getInstance();
//		System.out.println(c.get(Calendar.DAY_OF_WEEK));
//		System.out.println(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
//		System.out.println("2012/9/3 9:00".matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{2}"));
//		System.out.println(convertDateStr("2011/1/13  10:51:00", "yyyy-MM-dd HH:mm:ss"));
	}
}
