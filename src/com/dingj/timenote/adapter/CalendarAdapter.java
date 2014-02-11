package com.dingj.timenote.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingj.timenote.R;
import com.dingj.timenote.calendar.CalendarUtil;
import com.dingj.timenote.util.Util;

public class CalendarAdapter extends BaseAdapter
{
	private Context mContext;
	/**当前年份*/
	private int mYear;
	/**当前月份*/
	private int mMonth;
	/**当前天*/
	private int mDay;
	/**当前内容数组*/
	private String[] mDayArray;
	/**这个月的起始位置*/
	private int mDayStartIndex = 0;
	/**这个月的结束位置*/
	private int mDayEndIndex = 0;
	/**是否是润年*/
	private boolean bLeapYear = false;
	public CalendarAdapter(Context mContext)
	{
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount()
	{
		return mDayArray.length;
	}

	@Override
	public Object getItem(int arg0)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2)
	{
		if(view == null)
		{
			view = LinearLayout.inflate(mContext, R.layout.grid_item, null);
		}
		TextView textView = (TextView)view.findViewById(R.id.text);
		textView.setText(mDayArray[position].split("\\.")[0] + "\n" + mDayArray[position].split("\\.")[1]);
		textView.setHeight(Util.DISPLAY_HEIGHT / 15);
		//上个月和下个月字体偏淡
		if(position < 7)    //星期
		{
			textView.setTextColor(Color.RED);
		}
		else if(position < 7 + mDayStartIndex)
		{
			textView.setTextColor(Color.GRAY);
		}
		else if(position < 7 + mDayEndIndex)
		{
			textView.setTextColor(Color.BLACK);
		}
		else
		{
			textView.setTextColor(Color.GRAY);
		}
		if(position > 7)
		{
			int day = Integer.parseInt(mDayArray[position].split("\\.")[0]);
			if(CalendarUtil.isNowDay(mYear, mMonth, day))
			{
				textView.setTextColor(Color.BLUE);
			}
		}
		return view;
	}
	
	public void updateCalendar(int year,int month)
	{
		mYear = year;
		mMonth = month;
		mDayArray = CalendarUtil.getDayArray(mContext, year, month);
		bLeapYear = CalendarUtil.isLeapYear(mYear);
		mDayStartIndex = CalendarUtil.getWeekdayOfMonth(year, month);
		mDayEndIndex = mDayStartIndex + CalendarUtil.getDaysOfMonth(bLeapYear, month);
	}
	
	public void setCurrentDay(int day)
	{
		mDay = day;
	}
}
