package com.zy.sso.base.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**mysql时间写入与读取转换
	 *
	 * 读取 Date-String to_char(date,format)  
	 * 写入 String-Date to_timestamp(str,format) 
	 * =====================================================
	 * 读取 Date-String to_char(date,'yyyy-MM-dd hh24:mi:ss')   
	 * 写入 String-Date to_timestamp(str, 'yyyy-MM-dd hh24:mi:ss') 
	 * ======================================================
	 * 读取 Date-String to_char(date,'%Y-%m-%d %T')   
	 * 写入 String-Date to_timestamp(str, '%Y-%m-%d %T') 
	 * /
	 	 
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String to_char_LONG = "yyyy-MM-dd HH:mm:ss";
	
	public static final String to_char_LONG1 = "yyyy/MM/dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HHmmss
	 */
	public static final String to_char_LONG_NOCOMMA = "yyyy-MM-dd HHmmss";
	/**
	 * yyyy-MM-dd HHmmss.S
	 */
	public static final String to_char_LONG_NOCOMMAS = "yyyy-MM-dd HHmmss.SSS";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String to_char_MIDDLE = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd HHmm
	 */
	public static final String to_char_MIDDLE_NOCOMMA = "yyyy-MM-dd HHmm";

	/**
	 * yyyy-MM-dd
	 */
	public static final String to_char_SHORT = "yyyy-MM-dd";

	/**
	 * yyyyMMdd
	 */
	public static final String to_char_SHORT2 = "yyyyMMdd";

	/**
	 * yyyy/MM/dd
	 */
	public static final String to_char_SHORT3 = "yyyy/MM/dd";

	/**
	 * yyyy.MM.dd
	 */
	public static final String to_char_SHORT4 = "yyyy.MM.dd";

	/**
	 * ddMMMyy
	 */
	public static final String to_char_SHORT5 = "ddMMMyy";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String to_char_SHORT6 = "yyyyMMddHHmmss";
	
	public static final String DATE_FORMAT_SHORT6 = "yyyyMMddHHmmss";
	

	public static final String to_char_SHORT7 = "ddMMM";

	public static final String to_char_SHORT8 = "yyMMdd";
	
	public static final String to_char_SHORT9 = "yyyyMMddHHmmssSSS";
	/**
	 * 日期格式化为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (null != date && StringUtil.isNotNull(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.format(date);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	   * 获取现在时间
	   * @return 返回时间类型   Date
	   * @return 返回时间格式   yyyy-MM-dd HH:mm:ss
	   */
	public static Date getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   ParsePosition pos = new ParsePosition(8);
	   Date currentTime_2 = formatter.parse(dateString, pos);
	   return currentTime_2;
	}
	
	/**
	   * 获取现在时间
	   * @return 返回时间类型   String
	   * @return 返回时间格式   yyyy-MM-dd HH:mm:ss
	   */
	public static String getNowDateString() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   return dateString;
		}
}
