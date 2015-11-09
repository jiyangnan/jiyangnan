package com.dfjh.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dfjh.oper.facebook.FacebookChannelQueryDay;
import com.dfjh.oper.facebook.FacebookChannelQueryMonth;
import com.dfjh.oper.facebook.FacebookChannelQueryWeek;
import com.dfjh.oper.facebook.FacebookTVQueryDay;
import com.dfjh.oper.facebook.FacebookTVQueryMonth;
import com.dfjh.oper.facebook.FacebookTVQueryWeek;
import com.dfjh.oper.ggplus.GgplusChannelQueryDay;
import com.dfjh.oper.ggplus.GgplusChannelQueryMonth;
import com.dfjh.oper.ggplus.GgplusChannelQueryWeek;
import com.dfjh.oper.ggplus.GgplusTVQueryDay;
import com.dfjh.oper.ggplus.GgplusTVQueryMonth;
import com.dfjh.oper.ggplus.GgplusTVQueryWeek;
import com.dfjh.oper.twitter.TwitterChannelQueryDay;
import com.dfjh.oper.twitter.TwitterChannelQueryMonth;
import com.dfjh.oper.twitter.TwitterChannelQueryWeek;
import com.dfjh.oper.twitter.TwitterTVQueryDay;
import com.dfjh.oper.twitter.TwitterTVQueryMonth;
import com.dfjh.oper.twitter.TwitterTVQueryWeek;
import com.dfjh.oper.ytb.YtbChannelQueryDay;
import com.dfjh.oper.ytb.YtbChannelQueryMonth;
import com.dfjh.oper.ytb.YtbChannelQueryWeek;
import com.dfjh.oper.ytb.YtbTVQueryDay;
import com.dfjh.oper.ytb.YtbTVQueryMonth;
import com.dfjh.oper.ytb.YtbTVQueryWeek;
import com.dfjh.properties.PropertiesParse;

public class ToPlatform {
	public  void toPlatform() {
		String propertiesName = "poiDB.properties";
		String taday = PropertiesParse.parse("taday", propertiesName);
		String yesterday = null;
		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = sdf.format(date);
		Calendar cal = Calendar.getInstance();

		while(!taday.equals(currentTime)){
			System.out.println("执行今日日期："+taday);
			System.out.println("&&&&&&&&&&&&&&&    平台日榜栏目           &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(taday);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				yesterday = sdf.format(cal2.getTime());
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbChannelQueryDay().operFromTemp(taday, setNum,taday, yesterday);
				new TwitterChannelQueryDay().operFromTemp(taday, setNum, taday, yesterday);
				new FacebookChannelQueryDay().operFromTemp(taday, setNum ,taday, yesterday);
				new GgplusChannelQueryDay().operFromTemp(taday, setNum ,taday, yesterday);
				System.out.println("taday:"+taday);
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
		System.out.println("*     四大平台日榜栏目 已生成！！！                            *");
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
			System.out.println("&&&&&&&&&&&&&&&   平台周榜栏目          &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(tadayForWeek);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				yesterday = sdf.format(cal2.getTime());
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbChannelQueryWeek().operFromTemp(tadayForWeek, setNum);
				new TwitterChannelQueryWeek().operFromTemp(tadayForWeek, setNum);
				new FacebookChannelQueryWeek().operFromTemp(tadayForWeek, setNum);
				new GgplusChannelQueryWeek().operFromTemp(tadayForWeek, setNum);
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
		System.out.println("*     四大平台周榜栏目 已生成！！！                            *");
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
			System.out.println("&&&&&&&&&&&&&&&   平台月榜栏目          &&&&&&&&&&&&&&&&&");
			try {
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();
				Date myDate = sdf.parse(tadayForMonth);
				cal.setTime(myDate);
				cal2.setTime(cal.getTime());
				cal2.add(Calendar.DATE, -1);
				yesterday = sdf.format(cal2.getTime());
				cal3.setTime(cal.getTime());
				cal3.add(Calendar.DATE, +1);
				cal.add(Calendar.DATE, +1);
				new YtbChannelQueryMonth().operFromTemp(tadayForMonth, setNum);
				new TwitterChannelQueryMonth().operFromTemp(tadayForMonth, setNum);
				new FacebookChannelQueryMonth().operFromTemp(tadayForMonth, setNum);
				new GgplusChannelQueryMonth().operFromTemp(tadayForMonth, setNum);
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
		System.out.println("*     四大平台月榜栏目 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		
		
		taday = PropertiesParse.parse("taday", propertiesName);
		while(!taday.equals(currentTime)){
			System.out.println("执行今日日期："+taday);
			System.out.println("&&&&&&&&&&&&&&&   平台日榜机构          &&&&&&&&&&&&&&&&&");
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
				new YtbTVQueryDay().operFromTemp(taday, setNum);
				new TwitterTVQueryDay().operFromTemp(taday, setNum);
				new FacebookTVQueryDay().operFromTemp(taday, setNum);
				new GgplusTVQueryDay().operFromTemp(taday, setNum);
				System.out.println("taday:"+taday);
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
		System.out.println("*     四大平台日榜机构 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		tadayForWeek = PropertiesParse.parse("tadayForWeek", propertiesName);
		while(!tadayForWeek.equals(currentTime)){
			System.out.println("执行今日日期："+tadayForWeek);
			System.out.println("&&&&&&&&&&&&&&&   平台周榜机构          &&&&&&&&&&&&&&&&&");
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
				new YtbTVQueryWeek().operFromTemp(tadayForWeek, setNum);
				new TwitterTVQueryWeek().operFromTemp(tadayForWeek, setNum);
				new FacebookTVQueryWeek().operFromTemp(tadayForWeek, setNum);
				new GgplusTVQueryWeek().operFromTemp(tadayForWeek, setNum);
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
		System.out.println("*     四大平台周榜机构 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		tadayForMonth = PropertiesParse.parse("tadayForMonth", propertiesName);
		while(!tadayForMonth.equals(currentTime)){
			System.out.println("执行今日日期："+tadayForMonth);
			System.out.println("&&&&&&&&&&&&&&&   平台月榜机构          &&&&&&&&&&&&&&&&&");
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
				new YtbTVQueryMonth().operFromTemp(tadayForMonth, setNum);
				new TwitterTVQueryMonth().operFromTemp(tadayForMonth, setNum);
				new FacebookTVQueryMonth().operFromTemp(tadayForMonth, setNum);
				new GgplusTVQueryMonth().operFromTemp(tadayForMonth, setNum);
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
		System.out.println("*     四大平台月榜机构 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
	}
	
}
