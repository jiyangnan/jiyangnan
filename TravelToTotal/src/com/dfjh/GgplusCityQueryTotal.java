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
public class GgplusCityQueryTotal {
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
		
		
		//生成channel展示表
		String channelSql = "SELECT a.*,a.id as top_id,sum(b.view_count) as view_count," +
				"sum(b.good_count) as good_count ,sum(b.bad_count) as bad_count," +
				"sum(b.share_count) as share_count,sum(b.review_count) as review_count," +
				"sum(b.citems_count) as citems_count," +
				"b.update_time  " +
				"FROM ufeng_tops as a LEFT JOIN ufeng_ggplus_tops_channel_count_total as b " +
				"ON a.id = b.pid  WHERE a.level='2'  GROUP BY id";
		
		List<Map<String,Object>> channelMapList = null;
		try {
			Connection conn=null;
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement ps= null;
			String sql = "delete from ufeng_ggplus_tops_class_count_total ";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.execute();
			System.out.println("删除完成！");
			channelMapList = DBHelper.getQueryRunner().query(channelSql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.addListMapForTVCount(channelMapList, "ufeng_ggplus_tops_class_count_total");
		System.out.println("插入ufeng_ggplus_tops_class_count_total表大小为："+channelMapList.size());
		System.out.println("操作完成！");

	}
//	public static void main(String[] args) {
//		String propertiesName = "poiDB.properties";
//		String taday = PropertiesParse.parse("taday", propertiesName);
//		String yesterday = PropertiesParse.parse("yesterday", propertiesName);
//		int setNum = Integer.valueOf(PropertiesParse.parse("setNum", propertiesName));
//		System.out.println(taday +"  "+ yesterday);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		new GgplusTVQueryTotal().operFromTemp("2015-09-15", setNum);
//	}
}
