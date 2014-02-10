package com.dingj.timenote;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dingj.timenote.adapter.CalendarAdapter;
import com.dingj.timenote.calendar.CalendarUtil;
import com.pwp.dao.ScheduleDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class TimeNoteActivity extends Activity
{
	/**日历GridView*/
	private GridView mCalerdarGridView;
	private CalendarAdapter mCalendarAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init()
	{
		mCalerdarGridView = (GridView)findViewById(R.id.calendar_gridview);
		mCalendarAdapter = new CalendarAdapter();
		{
			CalendarUtil.
		}
		mCalerdarGridView.setAdapter(mCalendarAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
