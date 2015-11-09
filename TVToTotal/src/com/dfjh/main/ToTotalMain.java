package com.dfjh.main;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dfjh.FacebookChannelQueryTotal;
import com.dfjh.FacebookQueryIntoTempTotal;
import com.dfjh.FacebookTVQueryTotal;
import com.dfjh.GgplusChannelQueryTotal;
import com.dfjh.GgplusQueryIntoTempTotal;
import com.dfjh.GgplusTVQueryTotal;
import com.dfjh.TwitterChannelQueryTotal;
import com.dfjh.TwitterQueryIntoTempTotal;
import com.dfjh.TwitterTVQueryTotal;
import com.dfjh.YtbChannelQueryTotal;
import com.dfjh.YtbQueryIntoTempTotal;
import com.dfjh.YtbTVQueryTotal;

public class ToTotalMain {
	public  void justDoToTal() {
		int setNum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		Calendar calTmp = Calendar.getInstance();
		calTmp.setTime(date);
		calTmp.add(Calendar.DATE, -1);



		String tadayForTotal = sdf.format(calTmp.getTime());
			System.out.println("执行今日日期："+tadayForTotal);
			System.out.println("&&&&&&&&&&&&&&&   平台总榜中间表          &&&&&&&&&&&&&&&&&");
			try {
				new YtbQueryIntoTempTotal().oper(tadayForTotal);
				new TwitterQueryIntoTempTotal().oper(tadayForTotal);
				new FacebookQueryIntoTempTotal().oper(tadayForTotal);
				new GgplusQueryIntoTempTotal().oper(tadayForTotal);
				System.out.println("taday:"+tadayForTotal);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     四大平台总榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("中间表全部生成完毕！！！");




		tadayForTotal = sdf.format(calTmp.getTime());
			System.out.println("执行今日日期："+tadayForTotal);
			System.out.println("&&&&&&&&&&&&&&&   平台总榜栏目          &&&&&&&&&&&&&&&&&");
			try {
				new YtbChannelQueryTotal().operFromTemp(tadayForTotal, setNum);
				new TwitterChannelQueryTotal().operFromTemp(tadayForTotal, setNum);
				new FacebookChannelQueryTotal().operFromTemp(tadayForTotal, setNum);
				new GgplusChannelQueryTotal().operFromTemp(tadayForTotal, setNum);
				System.out.println("taday:"+tadayForTotal);
				tadayForTotal = sdf.format(cal.getTime());
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		System.out.println("   *********************************");
		System.out.println("  *                                   *");
		System.out.println("*     四大平台总榜栏目 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();



		tadayForTotal = sdf.format(calTmp.getTime());
		System.out.println("执行今日日期："+tadayForTotal);
		System.out.println("&&&&&&&&&&&&&&&   平台总榜机构          &&&&&&&&&&&&&&&&&");
		try {
			new YtbTVQueryTotal().operFromTemp(tadayForTotal, setNum);
			new TwitterTVQueryTotal().operFromTemp(tadayForTotal, setNum);
			new FacebookTVQueryTotal().operFromTemp(tadayForTotal, setNum);
			new GgplusTVQueryTotal().operFromTemp(tadayForTotal, setNum);
			System.out.println("taday:"+tadayForTotal);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("  *                                   *");
		System.out.println("*     四大平台总榜          已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
