package com.dfjh.ToHot.GgplusByClass;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dfjh.db.utilsTVHot.DBHelper;
import com.dfjh.db.utilsTVHot.zwdDBUtil;

public class HotPublishGgplusByClass {

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
		String sql = "delete from ufeng_ggplus_data_inner  where intodate='"+sdf.format(calendar.getTime())+"'";
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
	 * @param channelName
	 * @throws SQLException
	 */
	public void operHotPublishGgplusByClass(String channelName) throws SQLException{
		//实例化一个Calendar类   用来操作当前时间， 截取年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		//获取到当前时间的年月日后用来操作表明的生成
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH));
		int day = (calendar.get(Calendar.DATE));
		String month_str = String.format("%02d", month+1);
		String day_str = String.format("%02d", day-1);
		
		String[] strChar = null;
		String before_str = null;
		String middle_str = null;
		String after_str = null;
		String finnal_str = null;
		if (channelName.contains("\"")) {
			strChar = channelName.split("\"");
			before_str = strChar[0];
			middle_str = strChar[1];
			after_str = strChar[2];
			before_str = before_str + "\"";
			middle_str = middle_str+"\"";
			finnal_str = before_str +middle_str+after_str;
		}else{
			finnal_str = channelName;
		}
		String querySql = "SELECT * FROM wk_ggplus_data_"+year+"_"+month_str+"_"+day_str+" where " +
		"title like \"%"+finnal_str+"%\" and channel=\""+finnal_str+"\" ORDER BY uploadtime DESC LIMIT 0,10";
		List<Map<String, Object>> channelMapList = null;
		//执行查询
		channelMapList = DBHelper.getQueryRunner().query(querySql,new MapListHandler());
		System.out.println("channelMapLst的长度"+channelMapList.size());
		//当查到的list不为空时，执行插入方法；
		if (CollectionUtils.isNotEmpty(channelMapList)) {
			//实例化一个map  用来往查到的集合中添加一列数据
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapList.size(); i++) {
				map = channelMapList.get(i);
				map.put("neworhot", "new");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("执行插入一列数据后channelMapList的长度为"+channelMapList.size());
			zwdDBUtil.addListMap(channelMapList,"ufeng_ggplus_data_inner");
		}else{
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
		}
		System.out.println("操作完成！");
	}
	/**
	 * 热门发布的算法
	 * @param channelName
	 * @throws SQLException
	 */
	public void operGgplusByHot(String channelName) throws SQLException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH));
		int day = (calendar.get(Calendar.DATE));
		String month_str = String.format("%02d", month+1);
		String day_str = String.format("%02d", day-1);
		
		String[] strChar = null;
		String before_str = null;
		String middle_str = null;
		String after_str = null;
		String finnal_str = null;
		if (channelName.contains("\"")) {
			strChar = channelName.split("\"");
			before_str = strChar[0];
			middle_str = strChar[1];
			after_str = strChar[2];
			before_str = before_str + "\"";
			middle_str = middle_str+"\"";
			finnal_str = before_str +middle_str+after_str;
		}else{
			finnal_str = channelName;
		}
		String queryFacBookSqlByHotByHot = "SELECT * FROM wk_ggplus_data_"+year+"_"+month_str+"_"+day_str+" " +
				"where channel=\'"+finnal_str+"\' order by viewcount desc limit 0,10 " ;
		List<Map<String, Object>> channelMapListByHot = null;
		channelMapListByHot = DBHelper.getQueryRunner().query(queryFacBookSqlByHotByHot,new MapListHandler());
		System.out.println("channelMapLst的长度"+channelMapListByHot.size());
		if (CollectionUtils.isNotEmpty(channelMapListByHot)) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapListByHot.size(); i++) {
				map = channelMapListByHot.get(i);
				map.put("neworhot", "hot");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("执行新增一列数据后的channelMapListByHost长度为"+channelMapListByHot.size());
			zwdDBUtil.addListMap(channelMapListByHot,"ufeng_ggplus_data_inner");
		}else{
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
		}
		System.out.println("操作完成！");

	}
	/**
	 * 删除为null的数据
	 */
	public void delIsNullDateByYtb(){
		String sql = "delete from ufeng_ggplus_data_inner where isnull(neworhot)";
		int count = zwdDBUtil.delIsNullData(sql);
		if (count>0) {
			System.out.println("为null的数据已经处理完毕");
		}else{
			System.out.println("没有为null的数据");
		}
		
	}

}
