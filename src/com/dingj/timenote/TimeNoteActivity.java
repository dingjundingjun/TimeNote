package com.dingj.timenote;

import jding.debug.JDingDebug;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import com.dingj.timenote.adapter.CalendarAdapter;
import com.dingj.timenote.calendar.CalendarUtil;
import com.dingj.timenote.util.Util;

public class TimeNoteActivity extends Activity implements OnClickListener
{
	private boolean Debug = true;
	private String TAG = "TimeNoteActivity";
	/**日历GridView*/
	private GridView mCalerdarGridView;
	private CalendarAdapter mCalendarAdapter;
	/**当前显示的年份*/
	private int mCurrentYear;
	/**当前显示的月份*/
	private int mCurrentMonth;
	/**当前天*/
	private int mCurrentDay;
	private TextView mTextCalendarInfo;
	private Button mBtnLastMonth;
	private Button mBtnNextMonth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Util.getDisplaySize(this);
		init();
	}

	private void init()
	{
		mCalerdarGridView = (GridView)findViewById(R.id.calendar_gridview);
		mCalendarAdapter = new CalendarAdapter(this);
		mTextCalendarInfo = (TextView)findViewById(R.id.calendar_info);
		mBtnLastMonth = (Button)findViewById(R.id.lastmonth);
		mBtnNextMonth = (Button)findViewById(R.id.nextmonth);
		mBtnLastMonth.setOnClickListener(this);
		mBtnNextMonth.setOnClickListener(this);
		getTodayDate();
		mCalendarAdapter.setCurrentDay(mCurrentDay);
		mCalendarAdapter.updateCalendar(mCurrentYear, mCurrentMonth);
		mCalerdarGridView.setAdapter(mCalendarAdapter);
		jumpTo(mCurrentYear,mCurrentMonth);
	}
	
	/**
	 * 获取当天的日期
	 */
	private void getTodayDate()
	{
		String currentDate = CalendarUtil.getDate();
		mCurrentYear = Integer.parseInt(currentDate.split("-")[0]);
		mCurrentMonth = Integer.parseInt(currentDate.split("-")[1]);
		mCurrentDay = Integer.parseInt(currentDate.split("-")[2]);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) 
	{
		switch(item.getItemId())
		{
			case R.id.action_jump:
			{
				JDingDebug.printfD(TAG, "jump11111111111111111111111");
				int startYear = mCurrentYear;
				int startMonth = mCurrentMonth - 1;
				int startDay = mCurrentDay;
				new DatePickerDialog(this, mDateSetListener, startYear, startMonth, startDay).show();
				break;
			}
			case R.id.action_jump_today:
			{
				getTodayDate();
				jumpTo(mCurrentYear, mCurrentMonth);
				break;
			}
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	/**
	 * 日期控件的事件
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
	{
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{
			mCurrentYear = year;
			mCurrentMonth = monthOfYear + 1;
			mCurrentDay = dayOfMonth;
			jumpTo(mCurrentYear, mCurrentMonth);
		}
	};
	    
	/**
	 * 跳转到指定月份
	 * @param year
	 * @param month
	 */
	private void jumpTo(int year,int month)
	{
		if(CalendarUtil.isNowMonth(year, month))
		{
			mTextCalendarInfo.setText(mCurrentYear + "-" + mCurrentMonth + "-" + mCurrentDay);
		}
		else
		{
			mTextCalendarInfo.setText(mCurrentYear + "-" + mCurrentMonth + "-1");
		}
		mCalendarAdapter.updateCalendar(year, month);
		mCalendarAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.lastmonth:
			{
				mCurrentMonth--;
				if(mCurrentMonth < 1)
				{
					mCurrentMonth = 12;
					mCurrentYear--;
				}
				jumpTo(mCurrentYear, mCurrentMonth);
				break;
			}
			case R.id.nextmonth:
			{
				mCurrentMonth++;
				if(mCurrentMonth > 12)
				{
					mCurrentMonth = 1;
					mCurrentYear++;
				}
				jumpTo(mCurrentYear, mCurrentMonth);
				break;
			}
			default:
			{
				break;
			}
		}
	}
}
