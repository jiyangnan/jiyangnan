package com.dfjh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dfjh.ToHot.GgplusByClass.HotPublishGgplusByClass;
import com.dfjh.ToHot.TwitterByClass.HotPublishTwitterByClass;
import com.dfjh.ToHot.YTBByClass.HotPublishYTBByClass;
import com.dfjh.ToHot.faecbookByClass.HotPublishFaceBookByClass;
import com.dfjh.db.utils.DBHelper;

public class HotPublishByClassMain {
	 
	public  void doInnerPublish() throws SQLException {
		String propertiesName = "poiDB.properties";
		DBHelper.setFileName(propertiesName);
		HotPublishYTBByClass ytb = new HotPublishYTBByClass();
		HotPublishFaceBookByClass faceBook = new HotPublishFaceBookByClass();
		HotPublishTwitterByClass twitter = new HotPublishTwitterByClass();
		HotPublishGgplusByClass ggplus = new HotPublishGgplusByClass();
		List<Map<String, Object>> channelList = null;
		channelList = twitter.getChannel();
		List<String> list  = new ArrayList<String>();


		for (Map<String, Object> entry :channelList ) {
			list.add((entry.values().toString()).substring(1,(entry.values().toString()).length()-1));
		}
		//删除datetime=当前日期往前推三天的数据===============
		ytb.delTwoDayAgoHotOrNewData();
		faceBook.delTwoDayAgoHotOrNewData();
		twitter.delTwoDayAgoHotOrNewData();
		ggplus.delTwoDayAgoHotOrNewData();
		
		
//执行生成热门发布数据算法的方法========================
		
		for(String lists : list){
			System.out.println(lists);
			ytb.operTybByHot(lists);
		}
		
		for (String string : list) {
			System.out.println(string);
			twitter.operTwitterByClassByHot(string);
		}

//		
//		for (String string : list) {
//			System.out.println(string);
//			faceBook.operFaceBookByHot(string);
//		}
//		for (String string : list) {
//			System.out.println(string);
//			ggplus.operGgplusByHot(string);
//		}
		
		
		//执行生成最新发布数据算法的方法=======================
		for(String lists : list)
		{
			ytb.operHotPublishYTBByClass(lists);
		}
		
		for(String lists:list){
			twitter.operHotPublishTwitterByClass(lists);
		}
//		for(String lists:list){
//			System.out.println(lists);
//			faceBook.operHotPublishFacBookByClass(lists);
//		}
//		for(String lists : list){
//			System.out.println(lists);
//			ggplus.operHotPublishGgplusByClass(lists);
//		}
		
		
		
		//由于添加完数据以后存在为null的数据   遂写了个删除数据为null的列
		ytb.delIsNullDateByYtb();
		faceBook.delIsNullDateByYtb();
		ggplus.delIsNullDateByYtb();
		twitter.delIsNullDateByYtb();
	}
}
