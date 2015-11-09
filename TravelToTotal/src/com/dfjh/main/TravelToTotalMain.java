package com.dfjh.main;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dfjh.FacebookSiteQueryTotal;
import com.dfjh.FacebookQueryIntoTempTotal;
import com.dfjh.GgplusSiteQueryTotal;
import com.dfjh.GgplusQueryIntoTempTotal;
import com.dfjh.TwitterSiteQueryTotal;
import com.dfjh.TwitterQueryIntoTempTotal;
import com.dfjh.YtbSiteQueryTotal;
import com.dfjh.YtbQueryIntoTempTotal;

/**
 * @author jiyangnan
 * @date 2015-10-09 11:37:23
 */
public class TravelToTotalMain {
	public  void justDoToTal() {
		int setNum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calTmp = Calendar.getInstance();
		calTmp.setTime(date);
		calTmp.add(Calendar.DATE, -1);



		String tadayForTotal = sdf.format(calTmp.getTime());
			System.out.println("执行今日日期："+tadayForTotal);
			System.out.println("&&&&&&&&&&&&&&&   旅游平台总榜中间表          &&&&&&&&&&&&&&&&&");
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
		System.out.println("*     旅游四大平台总榜中间表 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("旅游中间表全部生成完毕！！！");




		tadayForTotal = sdf.format(calTmp.getTime());
			System.out.println("执行今日日期："+tadayForTotal);
			System.out.println("&&&&&&&&&&&&&&&   旅游平台  景点 总榜          &&&&&&&&&&&&&&&&&");
			try {
				new YtbSiteQueryTotal().operFromTemp(tadayForTotal, setNum);
				new TwitterSiteQueryTotal().operFromTemp(tadayForTotal, setNum);
				new FacebookSiteQueryTotal().operFromTemp(tadayForTotal, setNum);
				new GgplusSiteQueryTotal().operFromTemp(tadayForTotal, setNum);
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
		System.out.println("*     旅游四大平台 景点 总榜 已生成！！！                            *");
		System.out.println("  *                                   *");
		System.out.println("   *********************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();



//		tadayForTotal = sdf.format(calTmp.getTime());
//		System.out.println("执行今日日期："+tadayForTotal);
//		System.out.println("&&&&&&&&&&&&&&&   旅游平台总榜 城市          &&&&&&&&&&&&&&&&&");
//		try {
//			new YtbCityQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new TwitterCityQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new FacebookCityQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new GgplusCityQueryTotal().operFromTemp(tadayForTotal, setNum);
//			System.out.println("taday:"+tadayForTotal);
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
//		System.out.println("  *                                   *");
//		System.out.println("*     旅游四大平台总榜 城市         已生成！！！                            *");
//		System.out.println("  *                                   *");
//		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		
//		
//		
//		tadayForTotal = sdf.format(calTmp.getTime());
//		System.out.println("执行今日日期："+tadayForTotal);
//		System.out.println("&&&&&&&&&&&&&&&   旅游平台总榜 省份/直辖市          &&&&&&&&&&&&&&&&&");
//		try {
//			new YtbProvinceQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new TwitterProvinceQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new FacebookProvinceQueryTotal().operFromTemp(tadayForTotal, setNum);
//			new GgplusProvinceQueryTotal().operFromTemp(tadayForTotal, setNum);
//			System.out.println("taday:"+tadayForTotal);
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
//		System.out.println("  *                                   *");
//		System.out.println("*     旅游四大平台总榜 省份/直辖市         已生成！！！                            *");
//		System.out.println("  *                                   *");
//		System.out.println("   ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
	}
}
