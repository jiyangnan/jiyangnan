package com.dfjh.ToHot.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;


import com.dfjh.TimeUtil;
import com.dfjh.db.utilsTVHot.DBHelper;
import com.dfjh.db.utilsTVHot.DBUtil;

public class HotPublishTwitter {
	/***
	 * @describe 从wk_twitter_data_paste表中查询最新记录存入到ufeng_twitter_data
	 * 
	 * 
	 * */
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//String host = "localhost";
	String host = "120.25.160.211";
	String driver = "com.mysql.jdbc.Driver";
	//String port = ":3306";
	String port = ":15000";
	String myDB = "bigdaplatform";
	String url ="jdbc:mysql://"+host+port+"/"+myDB+"?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8";
	String user = "root";
	//String passwd = "root123";
	String passwd = "qazwsx123456";
	public void hotPublish(String date) throws ParseException{
		Date dd = null;
		String str = null;
		Calendar cal =Calendar.getInstance();
		String LimitStr = null;
		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);
		Connection conn=null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement ps= null;
		//获取最新上传记录的上传时间
		try {
			dd = sdf.parse(date);
			str = sdf.format(dd);
			System.out.println("解析出来的日期:"+str);
			cal.setTime(dd);
			cal.add(Calendar.DATE, -30);
			LimitStr = String.valueOf(sdf.format(cal.getTime()));
			Class.forName(driver);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//判断当前日期属于本年内第几周
		List<Integer> list2= new ArrayList<Integer>();
		TimeUtil tu = new TimeUtil();
		list2 = tu.getWeekOfYear(dd);
		String paramYear = String.valueOf(list2.get(0));
		String paramWeek = String.valueOf(list2.get(1));
		System.out.println(paramYear+"年第"+paramWeek+"周");
		System.out.println("LimitStr:"+LimitStr);
		System.out.println("dd:"+dd);
		System.out.println("str:"+str);
		if(paramYear.equals("2015")&& list2.get(1)<35){
			try {
				String sql = "delete from ufeng_twitter_data ";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.execute();
				System.out.println("删除完成！");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//从元数据表中查出热门发布所需要的数据
			String SQL = " SELECT * FROM wk_twitter_data_paste WHERE DATETIME='"+date+"' AND " +
			"UPLOADTIME>= DATE_ADD(NOW(),INTERVAL -7 DAY) limit 0,500"; 
			try {
				list = DBHelper.getQueryRunner().query(SQL, new MapListHandler());
				//添加到最终显示的表中
				DBUtil.addListMap(list, "ufeng_twitter_data");
				System.out.println("LimitStr:"+LimitStr);
				System.out.println("dd:"+dd);
				System.out.println("str:"+str);
				System.out.println(list.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else//已经分表
			try {
				String sql = "delete from ufeng_twitter_data ";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.execute();
				System.out.println("删除完成！");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String SQL = " SELECT * FROM wk_twitter_data_"+paramYear+"_"+paramWeek+" WHERE DATETIME='"+date+"' AND " +
			"UPLOADTIME>= DATE_ADD(NOW(),INTERVAL -7 DAY)limit 0,500"; 
			try {
				list = DBHelper.getQueryRunner().query(SQL, new MapListHandler());
				DBUtil.addListMap(list, "ufeng_twitter_data");
				System.out.println(list.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
