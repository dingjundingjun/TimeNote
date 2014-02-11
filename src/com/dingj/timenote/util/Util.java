package com.dingj.timenote.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class Util
{
	/** 屏幕宽度 */
	public static int DISPLAY_WIDTH;
	/** 屏幕高度 */
	public static int DISPLAY_HEIGHT;

	/**
	 * 获取屏幕分辨率
	 * 
	 * @param context
	 */
	public static void getDisplaySize(Context context)
	{
		Display display = ((WindowManager) context.getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		DISPLAY_WIDTH = display.getWidth();
		DISPLAY_HEIGHT = display.getHeight();
	}
}
