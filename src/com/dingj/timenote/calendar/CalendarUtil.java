package com.dingj.timenote.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jding.debug.JDingDebug;

import com.dingj.timenote.R;

import android.content.Context;
import android.util.Log;

public class CalendarUtil
{
	public static final int TYPE_YEAR = 0;
	public static final int TYPE_MONTH = 1;
	public static final int TYPE_DAY = 2;
	private static boolean DEBUG = false;
	private static String TAG = "CalendarUtil";
	final static String chineseNumber[] =
	{ "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
	static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
			"yyyy年MM月dd日");
	final static long[] lunarInfo = new long[]
	{ 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0,
			0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255,
			0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0,
			0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2,
			0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60,
			0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550,
			0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
			0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0,
			0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4,
			0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0,
			0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540,
			0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a,
			0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970,
			0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5,
			0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0,
			0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4,
			0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
			0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0,
			0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7,
			0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0,
			0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255,
			0x06d20, 0x0ada0 };

	// 农历部分假日
	final static String[] lunarHoliday = new String[]
	{ "0101 春节", "0115 元宵", "0505 端午", "0707 七夕情人", "0715 中元", "0815 中秋",
			"0909 重阳", "1208 腊八", "1224 小年", "0100 除夕" };

	// 公历部分节假日
	final static String[] solarHoliday = new String[]
	{ "0101 元旦", "0214 情人", "0308 妇女", "0312 植树", "0315 消费者权益日", "0401 愚人",
			"0501 劳动", "0504 青年", "0512 护士", "0601 儿童", "0701 建党", "0801 建军",
			"0808 父亲", "0909 毛泽东逝世纪念", "0910 教师", "0928 孔子诞辰", "1001 国庆",
			"1006 老人", "1024 联合国日", "1112 孙中山诞辰纪念", "1220 澳门回归纪念", "1225 圣诞",
			"1226 毛泽东诞辰纪念" };

	/**
	 * 获取当前的日期
	 */
	public static String getDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String currentDate = sdf.format(date);
		// int year_c = Integer.parseInt(currentDate.split("-")[0]);
		// int month_c = Integer.parseInt(currentDate.split("-")[1]);
		// int day_c = Integer.parseInt(currentDate.split("-")[2]);
		return currentDate;
	}

	/**
	 * 是否是当前月
	 */
	public static boolean isNowMonth(int year, int month)
	{
		String currentDate = CalendarUtil.getDate();
		int yearT = Integer.parseInt(currentDate.split("-")[0]);
		int monthT = Integer.parseInt(currentDate.split("-")[1]);
		int dayT = Integer.parseInt(currentDate.split("-")[2]);
		if (yearT == year && monthT == month)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 是否是今天的日期
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isNowDay(int year, int month, int day)
	{
		String currentDate = CalendarUtil.getDate();
		int yearT = Integer.parseInt(currentDate.split("-")[0]);
		int monthT = Integer.parseInt(currentDate.split("-")[1]);
		int dayT = Integer.parseInt(currentDate.split("-")[2]);
		JDingDebug
				.printfD(TAG, "year:" + year + " yearT:" + yearT + " month:"
						+ month + " monthT:" + monthT + " day:" + day
						+ " dayT:" + dayT);
		if (yearT == year && monthT == month && dayT == day)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 获取当前月的天数组
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[] getDayArray(Context context, int year, int month)
	{
		// 先获取当前月有多少天
		boolean bLeapyear = isLeapYear(year); // 是否为闰年
		int days = getDaysOfMonth(bLeapyear, month); // 某月的总天数
		int dayOfWeek = getWeekdayOfMonth(year, month); // 某月第一天为星期几
		int lastDaysOfMonth;
		if (month == 1) // 上一个月的总天数
		{
			bLeapyear = isLeapYear(year - 1);
			lastDaysOfMonth = getDaysOfMonth(bLeapyear, 12);
		} else
		{
			lastDaysOfMonth = getDaysOfMonth(bLeapyear, month - 1);
		}
		String[] dayArray = new String[42];
		int j = 1;
		String lunarDay = "";
		String[] weekArray = getWeekArray(context);
		// 得到当前月的所有日程日期(这些日期需要标记)
		for (int i = 0; i < dayArray.length; i++)
		{
			// 周一
			if (i < 7)
			{
				dayArray[i] = weekArray[i] + "." + " ";
			} else if (i < dayOfWeek + 7)
			{ // 前一个月
				int temp = lastDaysOfMonth - dayOfWeek + 1 - 7;
				lunarDay = getLunarDate(year, month - 1, temp + i, false);
				dayArray[i] = (temp + i) + "." + lunarDay;
			} else if (i < days + dayOfWeek + 7)
			{ // 本月
				String day = String.valueOf(i - dayOfWeek + 1 - 7); // 得到的日期
				lunarDay = getLunarDate(year, month, i - dayOfWeek + 1 - 7,
						false);
				dayArray[i] = i - dayOfWeek + 1 - 7 + "." + lunarDay;
				// 对于当前月才去标记当前日期
				/*
				 * if(sys_year.equals(String.valueOf(year)) &&
				 * sys_month.equals(String.valueOf(month)) &&
				 * sys_day.equals(day)){ //笔记当前日期 currentFlag = i; }
				 */
			} else
			{ // 下一个月
				lunarDay = getLunarDate(year, month + 1, j, false);
				dayArray[i] = j + "." + lunarDay;
				j++;
			}
		}

		/*
		 * String abc = ""; for(int i = 0; i < dayNumber.length; i++){ abc =
		 * abc+dayNumber[i]+":"; } Log.d("DAYNUMBER",abc);
		 */
		return dayArray;
	}

	/**
	 * 获取星期数组
	 * 
	 * @param context
	 * @return
	 */
	public static String[] getWeekArray(Context context)
	{
		String[] weekArray = context.getResources()
				.getStringArray(R.array.week);
		return weekArray;
	}

	// 将一个月中的每一天的值添加入数组dayNuber中
	private static void getweek(Context context, int year, int month)
	{

	}

	/**
	 * 是否是润年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year)
	{
		if (year % 100 == 0 && year % 400 == 0)
		{
			return true;
		} else if (year % 100 != 0 && year % 4 == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取某个月的总天数
	 * 
	 * @param isLeapyear
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(boolean bLeapyear, int month)
	{
		int days = 0;
		switch(month)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
			case 2:
				if (bLeapyear)
				{
					days = 29;
				} else
				{
					days = 28;
				}
		}
		return days;
	}

	/**
	 * 获取某月的第一天是星期几
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getWeekdayOfMonth(int year, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek;
	}

	/**
	 * 传出y年m月d日对应的农历. yearCyl3:农历年与1864的相差数 ? monCyl4:从1900年1月31日以来,闰月数
	 * dayCyl5:与1900年1月31日相差的天数,再加40 ?
	 * 
	 * isday: 这个参数为false---日期为节假日时，阴历日期就返回节假日 ，true---不管日期是否为节假日依然返回这天对应的阴历日期
	 * 
	 * @param cal
	 * @return
	 */
	public static String getLunarDate(int year_log, int month_log, int day_log,
			boolean isday)
	{
		// @SuppressWarnings("unused")
		int yearCyl, monCyl, dayCyl;
		// int leapMonth = 0;
		String nowadays;
		Date baseDate = null;
		Date nowaday = null;
		try
		{
			baseDate = chineseDateFormat.parse("1900年1月31日");
		} catch (ParseException e)
		{
			e.printStackTrace(); // To change body of catch statement use
			// Options | File Templates.
		}

		nowadays = year_log + "年" + month_log + "月" + day_log + "日";
		try
		{
			nowaday = chineseDateFormat.parse(nowadays);
		} catch (ParseException e)
		{
			e.printStackTrace(); // To change body of catch statement use
			// Options | File Templates.
		}

		// 求出和1900年1月31日相差的天数
		int offset = (int) ((nowaday.getTime() - baseDate.getTime()) / 86400000L);
		dayCyl = offset + 40;
		monCyl = 14;

		// 用offset减去每农历年的天数
		// 计算当天是农历第几天
		// i最终结果是农历的年份
		// offset是当年的第几天
		int iYear, daysOfYear = 0;
		for (iYear = 1900; iYear < 10000 && offset > 0; iYear++)
		{
			daysOfYear = yearDays(iYear);
			offset -= daysOfYear;
			monCyl += 12;
		}
		if (offset < 0)
		{
			offset += daysOfYear;
			iYear--;
			monCyl -= 12;
		}
		// 农历年份
		int year = iYear;

		yearCyl = iYear - 1864;
		int leapMonth = leapMonth(iYear); // 闰哪个月,1-12
		boolean leap = false;

		// 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
		int iMonth, daysOfMonth = 0;
		for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++)
		{
			// 闰月
			if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap)
			{
				--iMonth;
				leap = true;
				daysOfMonth = leapDays(year);
			} else
				daysOfMonth = monthDays(year, iMonth);

			offset -= daysOfMonth;
			// 解除闰月
			if (leap && iMonth == (leapMonth + 1))
				leap = false;
			if (!leap)
				monCyl++;
		}
		// offset为0时，并且刚才计算的月份是闰月，要校正
		if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1)
		{
			if (leap)
			{
				leap = false;
			} else
			{
				leap = true;
				--iMonth;
				--monCyl;
			}
		}
		// offset小于0时，也要校正
		if (offset < 0)
		{
			offset += daysOfMonth;
			--iMonth;
			--monCyl;
		}
		int month = iMonth;
		// setLunarMonth(chineseNumber[month - 1] + "月"); // 设置对应的阴历月份
		int day = offset + 1;
		if (!isday)
		{
			// 如果日期为节假日则阴历日期则返回节假日
			// setLeapMonth(leapMonth);
			for (int i = 0; i < solarHoliday.length; i++)
			{
				// 返回公历节假日名称
				String sd = solarHoliday[i].split(" ")[0]; // 节假日的日期
				String sdv = solarHoliday[i].split(" ")[1]; // 节假日的名称
				String smonth_v = month_log + "";
				String sday_v = day_log + "";
				String smd = "";
				if (month_log < 10)
				{
					smonth_v = "0" + month_log;
				}
				if (day_log < 10)
				{
					sday_v = "0" + day_log;
				}
				smd = smonth_v + sday_v;
				if (sd.trim().equals(smd.trim()))
				{
					return sdv;
				}
			}

			for (int i = 0; i < lunarHoliday.length; i++)
			{
				// 返回农历节假日名称
				String ld = lunarHoliday[i].split(" ")[0]; // 节假日的日期
				String ldv = lunarHoliday[i].split(" ")[1]; // 节假日的名称
				String lmonth_v = month + "";
				String lday_v = day + "";
				String lmd = "";
				if (month < 10)
				{
					lmonth_v = "0" + month;
				}
				if (day < 10)
				{
					lday_v = "0" + day;
				}
				lmd = lmonth_v + lday_v;
				if (ld.trim().equals(lmd.trim()))
				{
					return ldv;
				}
			}
		}
		if (day == 1)
			return chineseNumber[month - 1] + "月";
		else
			return getChinaDayString(day);

	}

	public static String getChinaDayString(int day)
	{
		String chineseTen[] =
		{ "初", "十", "廿", "卅" };
		int n = day % 10 == 0 ? 9 : day % 10 - 1;
		if (day > 30)
			return "";
		if (day == 10)
			return "初十";
		else
			return chineseTen[day / 10] + chineseNumber[n];
	}

	/**
	 * 农历 y年的总天数
	 * 
	 * @param y
	 * @return
	 */
	final private static int yearDays(int y)
	{
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1)
		{
			if ((lunarInfo[y - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapDays(y));
	}

	/**
	 * 传回农历 y年闰月的天数
	 * 
	 * @param y
	 * @return
	 */
	final private static int leapDays(int y)
	{
		if (leapMonth(y) != 0)
		{
			if ((lunarInfo[y - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
	 * 
	 * @param y
	 * @return
	 */
	final private static int leapMonth(int y)
	{
		return (int) (lunarInfo[y - 1900] & 0xf);
	}

	/**
	 * 传回农历 y年m月的总天数
	 * 
	 * @param y
	 * @param m
	 * @return
	 */
	final private static int monthDays(int y, int m)
	{
		if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
			return 29;
		else
			return 30;
	}

	/**
	 * 传回农历 y年的生肖
	 * 
	 * @param year
	 * @return
	 */
	final public String animalsYear(int year)
	{
		final String[] Animals = new String[]
		{ "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		return Animals[(year - 4) % 12];
	}
}
