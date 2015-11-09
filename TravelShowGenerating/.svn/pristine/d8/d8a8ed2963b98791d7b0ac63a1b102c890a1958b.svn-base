package com.dfjh.db.utils.travelShow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.StringUtils;

/**
 * @title	DBCommon
 * 	
 * @author	小炮的玫�?
 *
 * @description	dbcp数据库连接池
 * 
 * @creatTime	2015-4-29 下午02:06:42
 *
 * @ModifiedBy
 *
 * @ModifiedDate
 */
public class DBHelper {
	private static String fileName;
	private static DataSource ds;
	private static String username;
	private static String password;
	private static String url;
	private static String driver;
	
	private DBHelper(){
		
	}
	
	public static void setFileName(String fileName) {
		DBHelper.fileName = fileName;
	}

	public static void parse(){
		if(StringUtils.isNotEmpty(fileName)){
			Properties p = new Properties();
			try {
				File file = new File(fileName);
				InputStream in = new FileInputStream(file);
				p.load(in);
			//	p.load(DBHelper.class.getClassLoader().getResourceAsStream(fileName));
				username = p.getProperty("username");
				password = p.getProperty("password");
				url = p.getProperty("url");
				driver = p.getProperty("driver");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("未指定数据库配置");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static QueryRunner getQueryRunner(){
		if(null == ds){
			//解析
			parse();
			//配置dbcp数据�?
			BasicDataSource dbcpDataSource = new BasicDataSource();
			
			dbcpDataSource.setUsername(username);
			dbcpDataSource.setPassword(password);
			dbcpDataSource.setUrl(url);
			dbcpDataSource.setDriverClassName(driver);
			dbcpDataSource.setDefaultAutoCommit(true);
			//连接池最大数据库连接�?
			dbcpDataSource.setMaxActive(100);
			//�?��空闲数，数据库连接的�?��空闲时间。超过空闲时间，数据库连接将被标记为不可用，然后被释放�?设为0表示无限�?
			dbcpDataSource.setMaxIdle(30);
			//�?��建立连接等待时间。如果超过此时间将接到异常�?设为-1表示无限�?
			dbcpDataSource.setMaxWait(500);
			
			DBHelper.ds = dbcpDataSource;
			System.out.println("Initialize dbcp...");
		}
		return new QueryRunner(ds);
	}
	
//	public static void main(String[] args) throws SQLException {
//		QueryRunner qr = DBHelper.getQueryRunner();
//		Object value = qr.query("select pro_name,pro_price from product", new ScalarHandler());
//		System.out.println(value);
//	}

}
