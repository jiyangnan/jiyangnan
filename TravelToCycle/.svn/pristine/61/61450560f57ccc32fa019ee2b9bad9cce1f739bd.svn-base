package com.dfjh.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dfjh.oper.facebook.FacebookQueryIntoTempDay;
import com.dfjh.oper.facebook.FacebookQueryIntoTempMonth;
import com.dfjh.oper.facebook.FacebookQueryIntoTempWeek;
import com.dfjh.oper.ggplus.GgplusQueryIntoTempDay;
import com.dfjh.oper.ggplus.GgplusQueryIntoTempMonth;
import com.dfjh.oper.ggplus.GgplusQueryIntoTempWeek;
import com.dfjh.oper.twitter.TwitterQueryIntoTempDay;
import com.dfjh.oper.twitter.TwitterQueryIntoTempMonth;
import com.dfjh.oper.twitter.TwitterQueryIntoTempWeek;
import com.dfjh.oper.ytb.YtbQueryIntoTempDay;
import com.dfjh.oper.ytb.YtbQueryIntoTempMonth;
import com.dfjh.oper.ytb.YtbQueryIntoTempWeek;
import com.dfjh.properties.PropertiesParse;

public class TravelToTempMain {
	public  void toTemp() {
		String propertiesName = "poiDB.properties";
		String taday = PropertiesParse.parse("taday", propertiesName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = sdf.format(date);
		Calendar cal = Calendar.getInstance();

		while(!taday.equals(currentTime)){
			System.out.println("执行今日日期："+taday);
			System.out.println("&&&&&&&&&&&&&&&    旅游平台日榜中间表           &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(taday);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbQueryIntoTempDay().oper(taday);
				new TwitterQueryIntoTempDay().oper(taday);
				new FacebookQueryIntoTempDay().oper(taday);
				new GgplusQueryIntoTempDay().oper(taday);
				taday = sdf.format(cal.getTime());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     旅游四大平台日榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();



		String tadayForWeek = PropertiesParse.parse("tadayForWeek", propertiesName);
		//taday = "2015-08-17";
		while(!tadayForWeek.equals(currentTime)){
			System.out.println("执行今日日期："+tadayForWeek);
			System.out.println("&&&&&&&&&&&&&&&   旅游平台周榜中间表          &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(tadayForWeek);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbQueryIntoTempWeek().oper(tadayForWeek);
				new TwitterQueryIntoTempWeek().oper(tadayForWeek);
				new FacebookQueryIntoTempWeek().oper(tadayForWeek);
				new GgplusQueryIntoTempWeek().oper(tadayForWeek);
				System.out.println("taday:"+tadayForWeek);
				tadayForWeek = sdf.format(cal.getTime());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     旅游四大平台周榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();




		String tadayForMonth = PropertiesParse.parse("tadayForMonth", propertiesName);
		//taday = "2015-09-01";
		while(!tadayForMonth.equals(currentTime)){
			System.out.println("执行今日日期："+tadayForMonth);
			System.out.println("&&&&&&&&&&&&&&&   旅游平台月榜中间表          &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(tadayForMonth);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbQueryIntoTempMonth().oper(tadayForMonth);
				new TwitterQueryIntoTempMonth().oper(tadayForMonth);
				new FacebookQueryIntoTempMonth().oper(tadayForMonth);
				new GgplusQueryIntoTempMonth().oper(tadayForMonth);
				System.out.println("taday:"+tadayForMonth);
				tadayForMonth = sdf.format(cal.getTime());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     旅游四大平台月榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
}
