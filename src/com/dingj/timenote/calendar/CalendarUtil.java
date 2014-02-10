package com.dingj.timenote.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarUtil
{
	public static final int TYPE_YEAR = 0;
	public static final int TYPE_MONTH = 1;
	public static final int TYPE_DAY = 2;
	/**
	 * 获取当前的日期
	 */
	public static String getDate()
	{
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    	String currentDate = sdf.format(date);
//    	int year_c = Integer.parseInt(currentDate.split("-")[0]);
//    	int month_c = Integer.parseInt(currentDate.split("-")[1]);
//    	int day_c = Integer.parseInt(currentDate.split("-")[2]);
    	return currentDate;
	}

	
}
