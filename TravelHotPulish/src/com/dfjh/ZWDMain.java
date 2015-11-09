package com.dfjh;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZWDMain {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -2);
		//calendar.add(Calendar.MONTH, -1);
		System.out.println(sdf.format(calendar.getTime()));
		int month = calendar.get(Calendar.MONTH);
		String str = String.format("%02d", month+1);
		System.out.println(str);
	}
}
