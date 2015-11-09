package com.dfjh.ToHot.faecbookByClass;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utils.DBHelper;
import com.dfjh.db.utils.zwdDBUtil;

public class HotPublishFaceBookByClass {
	String propertiesName = "poiDB.properties";
	/**
	 * 删除热门发布和最新发布表中当前时间往前推两天的数据
	 */
	public int delTwoDayAgoHotOrNewData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -3);
		System.out.println(sdf.format(calendar.getTime()));
		String sql = "delete from ufeng_facebook_data_inner where intodate='"+sdf.format(calendar.getTime())+"'";
		int count = zwdDBUtil.delIsNullData(sql);
		if (count>0) {
			System.out.println(sdf.format(calendar.getTime())+"日的数据已经清空");
		}else {
			System.out.println("没有关于"+sdf.format(calendar.getTime())+"日的数据");
		}
		return count;
	}
	/**
	 * 最新发布的算法
	 * 
	 * @param channelName
	 * @throws SQLException
	 */

	public void operHotPublishFacBookByClass(String channelName)
			throws SQLException {
		// 实例化Calendar 用来操作时间，用于动态生成表表明
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH));
		int day = (calendar.get(Calendar.DATE));
		String month_str = String.format("%02d", month+1);
		String day_str = String.format("%02d", day-1);
		
		String[] strChar = null;
		String before_str = null;
		String after_str = null;
		String finnal_str = null;
		if (channelName.contains("\"")) {
			strChar = channelName.split("\"");
			before_str = strChar[0];
			after_str = strChar[1];
			before_str = before_str + "\"";
			finnal_str = before_str + after_str;
		} else if (channelName.contains("%")) {
			strChar = channelName.split("%");
			before_str = strChar[0];
			after_str = strChar[1];
			before_str = before_str +"\\%";
			finnal_str = before_str + after_str;
		} else {
			finnal_str = channelName;
		}
		String queryFacBookSql = "SELECT * FROM wk_facebook_data_" + year
				+ "_" + month_str + "_" + day_str + " where "
				+ "content like \"%"+finnal_str+"%\" and channel=\""
				+ finnal_str + "\" ORDER BY uploadtime DESC LIMIT 0,10";
		List<Map<String, Object>> channelMapList = null;
		channelMapList = DBHelper.getQueryRunner().query(queryFacBookSql,
				new MapListHandler());
		System.out.println("channelMapLst的长度" + channelMapList.size());
		if (CollectionUtils.isNotEmpty(channelMapList)) {
			// 实例化一个map 用来实现往数据集合中插入一条表示列
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapList.size(); i++) {
				map = channelMapList.get(i);
				map.put("neworhot", "new");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("插入数据后的ChannelMapList的长度"
					+ channelMapList.size());
			zwdDBUtil.addListMap(channelMapList, "ufeng_facebook_data_inner");
		} else {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
		}
		System.out.println("操作完成！");
	}

	/**
	 * 用于生成热门发布的算法
	 * 
	 * @param channelName
	 * @throws SQLException
	 */
	public void operFaceBookByHot(String channelName) throws SQLException {
		// 操作时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH));
		int day = (calendar.get(Calendar.DATE));
		String month_str = String.format("%02d", month+1);
		String day_str = String.format("%02d", day-1);
		String queryFacBookSqlByHot = "SELECT * FROM wk_facebook_data_" + year
				+ "_" + month_str + "_" + day_str+" where channel=\""+channelName+"\" and content like \"%"+channelName+"%\" order by viewcount desc limit 0,10 ";
		List<Map<String, Object>> channelMapListByHot = null;
		channelMapListByHot = DBHelper.getQueryRunner().query(
				queryFacBookSqlByHot, new MapListHandler());
		System.out.println("channelMapLst的长度" + channelMapListByHot.size());
		if (CollectionUtils.isNotEmpty(channelMapListByHot)) {
			// 如果这个集合不为空的话，就实例化一个map用来往这个集合中添加一个标识列
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapListByHot.size(); i++) {
				map = channelMapListByHot.get(i);
				map.put("neworhot", "hot");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("在执行插入标识列之后channelMapList的长度为"
					+ channelMapListByHot.size());
			zwdDBUtil.addListMap(channelMapListByHot,
					"ufeng_facebook_data_inner");
		} else {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
		}
		System.out.println("操作完成！");
	}
	
	
	/**
	 * 删除为null的数据
	 */
	public void delIsNullDateByYtb(){
		String sql = "delete from ufeng_facebook_data_inner where isnull(neworhot)";
		int count = zwdDBUtil.delIsNullData(sql);
		if (count>0) {
			System.out.println("为null的数据已经处理完毕");
		}else{
			System.out.println("没有为null的数据");
		}
		
	}

}
