package com.dfjh.db.utils.travelShow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class TimeUtil {
	
	/**
	 * @describe 设置Calender类
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public  Calendar setCalendar(int year,int month,int date){
		Calendar cl = Calendar.getInstance();
		cl.set(year, month-1, date);
		return cl;
	}

	/**
	 * @describe 获取某日前一天时间
	 * @param cl
	 * @return
	 */
	public  Calendar getBeforeDay(Calendar cl){
		int day = cl.get(Calendar.DATE);
		cl.set(Calendar.DATE, day-1);
		return cl;
	}


	/**
	 * @describe 获取某日下一天时间
	 * @param cl
	 * @return
	 */
	public  Calendar getNextDay(Calendar cl){
		int day = cl.get(Calendar.DATE);
		cl.set(Calendar.DATE, day+1);
		return cl;
	}


	/**
	 * 打印日期字符串
	 * @param cl
	 */
	public  void printCalendar(Calendar cl){
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH)+1;
		int day = cl.get(Calendar.DATE);
		System.out.println(year+"/"+month+"/"+day);
	}


	/**
	 *@describe 返回日期字符串
	 * @param cl
	 */
	public String returnDateString(Calendar cl){
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH)+1;
		int day = cl.get(Calendar.DATE);
		String dateString = null;
		dateString  = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
		return dateString;
	}
	/***
	 * @author jiyangnan
	 * @describe 采取法令时日期格式  获取一年中的某日属于第几周
	 * @return int
	 * 
	 * */
	public  List<Integer> getWeekOfYearFR(Date date) { 
		Locale.setDefault(Locale.FRANCE) ;
		List<Integer> list= new ArrayList<Integer>();
		Calendar c = new GregorianCalendar(); 
		//		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		//		 c.setMinimalDaysInFirstWeek(7); 
		c.setTime (date);
		System.out.println("当前日期为："+date);
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		list.add(year);
		list.add(week);
		return  list;
	}

	/***
	 * @author jiyangnan
	 * @describe 采取美令时日期格式  获取一年中的某日属于第几周
	 * @return int
	 * */
	public  List<Integer> getWeekOfYearUS(Date date){
		Locale.setDefault(Locale.US) ;
		List<Integer> list= new ArrayList<Integer>();
		Calendar c = new GregorianCalendar(); 
		c.setTime (date);
		System.out.println("当前日期为："+date);
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		list.add(year);
		list.add(week);
		return  list;
	}

	/** 
	 * @describe 获取一周的最后一天
	 * 
	 * @param year 
	 * @param week 
	 * @return 
	 */ 
	public  Date getLastDayOfWeek(int year, int week) { 
		Calendar c = new GregorianCalendar(); 
		c.set(Calendar.YEAR, year); 
		c.set(Calendar.MONTH, Calendar.JANUARY); 
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone(); 
		cal.add(Calendar.DATE , week * 7);
		return getLastDayOfWeek(cal.getTime()); 
	}

	/** 
	 * @describe 获取一周的最后一天
	 * 
	 * @param date 
	 * @return 
	 */ 
	public  Date getLastDayOfWeek(Date date) { 
		Calendar c = new GregorianCalendar(); 
		c.setFirstDayOfWeek(Calendar.SUNDAY); 
		c.setTime(date); 
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
		return c.getTime(); 
	} 
	   
	 public static void main(String[] args) {
		TimeUtil tu = new TimeUtil();
		@SuppressWarnings("unused")
		Date date = new Date();
		System.out.println(tu.getLastDayOfWeek(2015, 33));
	}
}
