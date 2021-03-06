package com.dfjh.ToHot.YTBByClass;

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

public class HotPublishYTBByClass {

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
		String sql = "delete from ufeng_ytb_data_inner where intodate='"+sdf.format(calendar.getTime())+"'";
		int count = zwdDBUtil.delIsNullData(sql);
		if (count>0) {
			System.out.println(sdf.format(calendar.getTime())+"日的数据已经清空");
		}else {
			System.out.println("没有关于"+sdf.format(calendar.getTime())+"日的数据");
		}
		return count;
	}
	/**
	 * 此方法用以生成最新发布
	 * @param channelName
	 * @throws SQLException
	 */
	public void operHotPublishYTBByClass(String channelName) throws SQLException{
		//实例化一个Calendar类 用来处理日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		//获得当前日期的年月日，用来灵活运行表名
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH));
		int day = (calendar.get(Calendar.DATE));
		String month_str = String.format("%02d", month+1);
		String day_str = String.format("%02d", day-1);
//		DBUtil du = new DBUtil();
		
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
		//编写查询总表记录的sql
		String querySql = "SELECT * FROM wk_ytb_data_"+year+"_"+month_str+"_"+day_str+" where " +
		"content like \"%"+finnal_str+"%\" and channel=\""+finnal_str+"\" ORDER BY uploadtime DESC LIMIT 0,10";
		List<Map<String, Object>> channelMapList = null;
		//执行查询，这里是抛出的异常
		channelMapList = DBHelper.getQueryRunner().query(querySql,new MapListHandler());
		System.out.println("channelMapLst的长度"+channelMapList.size());
		if(channelMapList!=null && channelMapList.size()>0){
			//实例化一个map 往查询到的list集合中添加一列；
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapList.size(); i++) {
				map = channelMapList.get(i);
				map.put("neworhot", "new");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("添加一列之后channelMapLst的长度"+channelMapList.size());
			if (CollectionUtils.isNotEmpty(channelMapList)) {
				zwdDBUtil.addListMap(channelMapList,"ufeng_ytb_data_inner");
			}else{
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
			}
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
	public void operTybByHot(String channelName) throws SQLException{
		//实例化一个Calendar  操作当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		//获取当前时间的年月日
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
		String querytybSqlByHotByHot = "SELECT * FROM wk_ytb_data_"+year+"_"+month_str+"_"+day_str+" where " +
				"content like \"%"+finnal_str+"%\" and channel=\""+finnal_str+"\" order by viewcount desc limit 0,10 " ;
		List<Map<String, Object>> channelMapListByHot = null;
		//执行查询  这里是抛的异常
		channelMapListByHot = DBHelper.getQueryRunner().query(querytybSqlByHotByHot,new MapListHandler());

		System.out.println("channelMapLst的长度"+channelMapListByHot.size());
		//如果查到的集合不为空，执行插入，为空则输出提示
		if (CollectionUtils.isNotEmpty(channelMapListByHot)&& channelMapListByHot!=null) {
			//实例化一个map  用来往查询到的集合中添加一列数据，
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < channelMapListByHot.size(); i++) {
				map = channelMapListByHot.get(i);
				map.put("neworhot", "hot");
				map.put("intodate", sdf.format(new Date()));
			}
			System.out.println("添加一列之后channelMapLst的长度"+channelMapListByHot.size());
			zwdDBUtil.addListMap(channelMapListByHot,"ufeng_ytb_data_inner");
		}else{
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&集合中数据为空");
		}
		System.out.println("操作完成！");
	}
	public void delIsNullDateByYtb(){
		String sql = "delete from ufeng_ytb_data_inner where isnull(neworhot)";
		int count = zwdDBUtil.delIsNullData(sql);
		if (count>0) {
			System.out.println("为null的数据已经处理完毕");
		}else{
			System.out.println("没有为null的数据");
		}

	}
}
