package com.dfjh;

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

import com.dfjh.db.utils.travelTotal.DBHelper;
import com.dfjh.db.utils.travelTotal.DBUtil;
import com.dfjh.db.utils.travelTotal.TimeUtil;
/**
 * @author jiyangnan
 * @date 2015-10-09 11:37:23
 */
public class FacebookSiteQueryTotal {
	String propertiesName = "poiDB.properties";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<Integer> intList = new ArrayList<Integer>();
	String host = "120.25.160.211";
	String driver = "com.mysql.jdbc.Driver";
	String port = ":15000";
	String myDB = "bigdatatravel";
	String url ="jdbc:mysql://"+host+port+"/"+myDB+"?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8";
	String user = "root";
	String passwd = "qazwsx123456";
	public void operFromTemp(String time,int setNum){
		Date myDate1 = null;
		Calendar cal = Calendar.getInstance();
		try {
			myDate1 = sdf.parse(time);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//判断当前日期属于本年内第几周
		TimeUtil tu = new TimeUtil();
		intList = tu.getWeekOfYearFR(myDate1);
		String paramYear = String.valueOf(intList.get(0));
		String paramWeek = String.valueOf(intList.get(1));

		String month = String .valueOf(cal.get(Calendar.MONTH)+1);
		System.out.println("当前时间为："+paramYear+"年"+month+"月"+paramWeek+" 周...  传入time为："+time);

		//获取数据库连接配置
		DBHelper.setFileName(propertiesName);

		try {
			Connection conn=null;
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement ps= null;
			String sql = "delete from ufeng_facebook_tops_channel_count_total ";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.execute();
			System.out.println("删除完成！");
			String channelJudge = "SELECT  update_time from ufeng_facebook_tops_channel_count_total where update_time='"+time+"' limit 0,10";
			List<Map<String,Object>> judgeMapList = null;
			judgeMapList = DBHelper.getQueryRunner().query(channelJudge, new MapListHandler());

			if(judgeMapList!=null && judgeMapList.size()>1){
				System.out.println("已生成"+time+"号数据！！！");
			}else{
				//生成channel展示表
				String channelSql = "SELECT  a.*,a.id as top_id ,d.SHARECOUNT as share_count,d.GOODCOUNT as good_count," +
						"d.REVIEWCOUNT as review_count ,d.CITEMS as citems_count," +
						"d.DATETIME as update_time , b.`LEVEL` AS na " +
						"FROM ufeng_tops as a JOIN wk_mapcode AS b  ON a.title = b.channel JOIN " +
						"wk_facebook_data_channel_date_total as d ON b.channel = d.channel and d.DATETIME='"+time+"' group by title";
				List<Map<String,Object>> channelMapList = null;
				channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
				System.out.println("插入ufeng_facebook_tops_channel_count_total表大小为："+channelMapList.size());
				DBUtil.addListMapForClassCount(channelMapList, "ufeng_facebook_tops_channel_count_total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("操作完成！！！");
	}
	//	public static void main(String[] args) {
	//		String propertiesName = "poiDB.properties";
	//		String taday = PropertiesParse.parse("taday", propertiesName);
	//		String yesterday = PropertiesParse.parse("yesterday", propertiesName);
	//		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
	//		System.out.println(taday +"  "+ yesterday);
	//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//		Date date = new Date();
	//		new FacebookChannelQueryTotal().operFromTemp("2015-09-15", setNum);
	//	}
}
